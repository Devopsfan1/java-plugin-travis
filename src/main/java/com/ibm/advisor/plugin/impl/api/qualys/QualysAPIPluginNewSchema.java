package com.ibm.advisor.plugin.impl.api.qualys;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.advisor.dto.PluginRQ;
import com.ibm.advisor.plugin.common.NetworkScanApiPlugin;
import com.ibm.advisor.plugin.intf.AdvisorPluginInterface;
import com.ibm.advisor.plugin.util.PluginsEnum;
import com.ibm.advisor.plugin.util.Utility;
import com.ibm.dim.*;
import org.apache.http.Header;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.*;

@Component
public class QualysAPIPluginNewSchema extends NetworkScanApiPlugin implements AdvisorPluginInterface {

    static final Logger log = LoggerFactory.getLogger(QualysAPIPluginNewSchema.class);
    static final String ACTION = "action";
    static final String API_URL = "api_url";
    private static final int QUALYS_QID_86002 = 86002;
    private static final int QUALYS_QID_38116 = 38116;
    static HttpClientContext context = HttpClientContext.create();
    static Header commonHdr = new BasicHeader("X-Requested-With", "Facklers PyQual python primer");

    @Override
    public ScanDataDim fetchScanResult(PluginRQ pluginRQ, long scanId) {
        log.info("Running Qualys API Plugin: {} ", pluginRQ);
        ScanDataDim scanDataDim = new ScanDataDim();
        try {
            //String dataStr = Utility.readFileAsString("C:\\Users\\002W3I744\\Downloads\\2023-08-16_18_09_08-data.json");
            connectQualys(pluginRQ.getExternal_param());
           String dataStr = fetchQualysDetails(pluginRQ.getExternal_param()); 
            parseQualysData(scanDataDim, dataStr, scanId);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Error during Qualys API Plugin execution: {}", e.getMessage());
        }finally {
			try{disconnectQualys(pluginRQ.getExternal_param());} catch (Exception e) {e.printStackTrace();}
		}
        return scanDataDim;
    }

    private void parseQualysData(ScanDataDim scanDataDim, String dataStr, long scanId) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        QualysDataModel[] qualysDataModelList = mapper.readValue(dataStr, QualysDataModel[].class);
        for (QualysDataModel qualysDataModel : qualysDataModelList) {
            if (qualysDataModel.getLaunch_date() != null) {
                scanDataDim.setScanDate(Utility.toLocalDate(qualysDataModel.getLaunch_date(), "M/d/yyyy HH:mm:ss"));
            }
            if (qualysDataModel.getIp() != null) {
                parseAllElements(scanDataDim, qualysDataModel, scanId);
            }
        }
    }

    private String performQualysRequest(Map<String, String> map, String endpoint, String action, NameValuePair... params)
            throws IOException {
        HttpPost post = new HttpPost(map.get(API_URL) + endpoint);
        ArrayList<NameValuePair> urlParameters = new ArrayList<>(Arrays.asList(params));
        urlParameters.add(new BasicNameValuePair(ACTION, action));
        post.setEntity(new UrlEncodedFormEntity(urlParameters));
        post.addHeader(commonHdr);
        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(post, context)) {
            return EntityUtils.toString(response.getEntity());
        }
    }

    private void connectQualys(Map<String, String> map) {
        try {
            performQualysRequest(map, "session/", "login",
                    new BasicNameValuePair("username", map.get("api_username")),
                    new BasicNameValuePair("password", map.get("api_password")));
        } catch (IOException e) {
            log.error("Error connecting to Qualys: {}", e.getMessage());
        }
    }

    private String fetchQualysDetails(Map<String, String> map) throws IOException {
        return performQualysRequest(map, "scan/", "fetch",
                new BasicNameValuePair("scan_ref", map.get("scan_search_id")),
                new BasicNameValuePair("output_format", "json_extended"),
                new BasicNameValuePair("mode", "extended"));
    }

    private void disconnectQualys(Map<String, String> map) {
        try {
            performQualysRequest(map, "session/?action=logout", "logout");
        } catch (IOException e) {
            log.error("Error disconnecting from Qualys: {}", e.getMessage());
        }
    }


    private void parseAllElements(ScanDataDim scanDataDim, QualysDataModel qualysDataModel, long scanId) {
        long qid = qualysDataModel.getQid();
        if (qid == QUALYS_QID_86002) {
            log.info("Parsing 86002 qualys Data Model");
            parseQid_86002_Info(scanDataDim, qualysDataModel, scanId);

        }
        if (qid == QUALYS_QID_38116) {
            log.info("Parsing 38116 qualys Data Model");
            parseQid_38116_Info(scanDataDim, qualysDataModel, scanId);
        }
    }

    private void parseQid_86002_Info(ScanDataDim scanData, QualysDataModel qualysDataModel, long scanId) {
        String ip = qualysDataModel.getIp();
        String dns = qualysDataModel.getDns();
        String port = qualysDataModel.getPort();
        String results = qualysDataModel.getResults();

        UUID netLocUUID = UUID.randomUUID();
        Netloc_Dim netLocDim = new Netloc_Dim();
        netLocDim.setNetloc_uuid(netLocUUID);
        netLocDim.setNetloc_host(ip);
        netLocDim.setNetloc_dns(dns);

        if (port != null && !"N/A".equalsIgnoreCase(port)) {
            netLocDim.setNetloc_port(port);
        }

        HashMap<String, String> certMap = new HashMap<>();
        String[] lines = results.split("\n");
        for (String line : lines) {
            String[] linerArr = line.split("\t");
            String key = linerArr[0];
            String val = "";
            if (linerArr[0].startsWith("(")) {
                key = key.substring(3);
            }
            if (linerArr.length > 1) {
                val = linerArr[1].trim();
            }
            certMap.put(key, val);
        }
        UUID certificateEdfId = UUID.randomUUID();
        Cert_Dim certDim = new Cert_Dim();
        certDim.setCert_uuid(certificateEdfId);
        String issuer = certMap.get("ISSUER NAME");
        if (issuer == null || issuer.isEmpty()) {
            issuer = "N/A";
        }
        certDim.setCert_issuer(issuer);
        certDim.setCert_sig_algo(certMap.get("Signature Algorithm"));
        certDim.setCert_algo(certMap.get("Public Key Algorithm"));
        certDim.setCert_not_after(certMap.get("Valid From").substring(0, certMap.get("Valid From").length() - 4));
        certDim.setCert_not_before(certMap.get("Valid Till").substring(0, certMap.get("Valid Till").length() - 4));
        certDim.setCert_subject(certMap.get("SUBJECT NAME"));

        String certVer = certMap.get("Version");
        if (certVer != null && certVer.contains("(")) {
            certVer = certVer.split("[(]")[0].trim();
        }
        certDim.setCert_ver(certVer);

        Scan_Nl_Cert_Fact scanNlCertFact = new Scan_Nl_Cert_Fact();
        scanNlCertFact.setCert_uuid(certificateEdfId);
        scanNlCertFact.setScan_id((int) scanId);
        scanNlCertFact.setNetloc_uuid(netLocUUID);

        scanData.getDims().add(netLocDim);
        scanData.getDims().add(certDim);
        scanData.getFacts().add(scanNlCertFact);
    }

    private void parseQid_38116_Info(ScanDataDim scanData, QualysDataModel qualysDataModel, long scanId) {
        String ip = qualysDataModel.getIp();
        String dns = qualysDataModel.getDns();
        String port = qualysDataModel.getPort();
        String results = qualysDataModel.getResults();

        //netIp start
        UUID netLocUUID = UUID.randomUUID();
        Netloc_Dim netLocDim = new Netloc_Dim();
        netLocDim.setNetloc_uuid(netLocUUID);
        netLocDim.setNetloc_host(ip);
        netLocDim.setNetloc_dns(dns);

        if (port != null && !"N/A".equalsIgnoreCase(port)) {
            netLocDim.setNetloc_port(port);
        }

        String[] lines = results.split("\n");
        UUID cryptographicProtocolEdfId = null;
        for (String line : lines) {
            if (line.startsWith("CIPHER")) {
                continue;
            }
            String[] valArr = line.split("\t");

            if (line.startsWith("TLSv") || line.startsWith("SSLv")) {
                String[] protocolStr = valArr[0].split(" ");
                String protocolType = protocolStr[0].substring(0, 3);
                String version = protocolStr[0].substring(4);
                String status = protocolStr[protocolStr.length - 1];
                if ("ENABLED".equalsIgnoreCase(status)) {
                    cryptographicProtocolEdfId = UUID.randomUUID();
                    Protocol_Dim protocolDim = new Protocol_Dim();
                    protocolDim.setProtocol_uuid(cryptographicProtocolEdfId);
                    protocolDim.setProtocol_name(valArr[0]);
                    protocolDim.setProtocol_type(protocolType);
                    protocolDim.setProtocol_ver(version);
                    scanData.getDims().add(protocolDim);
                }
            } else {
                UUID cipherSuiteEdfId = UUID.randomUUID();
                Ciphersuite_Dim cipherSuiteDim = new Ciphersuite_Dim();
                cipherSuiteDim.setCiphersuite_uuid(cipherSuiteEdfId);
                cipherSuiteDim.setCiphersuite_name(valArr[0]);
                cipherSuiteDim.setCiphersuite_strength((valArr[5]));
                cipherSuiteDim.setCiphersuite_enc_algo(valArr[0] + ":" + valArr[3]);
                cipherSuiteDim.setCiphersuite_pkenc(valArr[0] + ":" + valArr[4]);
                cipherSuiteDim.setCiphersuite_enc_algo_keylength(Integer.valueOf(valArr[4].substring(valArr[4].indexOf("(") + 1, valArr[4].indexOf(")"))));

                Scan_Nl_Cp_Cs_Fact scanNlCpCsFact = new Scan_Nl_Cp_Cs_Fact();
                scanNlCpCsFact.setCiphersuite_uuid(cipherSuiteEdfId);
                scanNlCpCsFact.setNetloc_uuid(netLocUUID);
                scanNlCpCsFact.setProtocol_uuid(cryptographicProtocolEdfId);
                scanNlCpCsFact.setScan_id((int) scanId);

                scanData.getDims().add(cipherSuiteDim);
                scanData.getFacts().add(scanNlCpCsFact);
            }
        }
        scanData.getDims().add(netLocDim);
    }

    @Override
    public boolean supports(PluginRQ pluginRQ) {
        log.info("Cond {}", pluginRQ.getPlugin_id() == PluginsEnum.API_QUALYS.pluginId);
        return pluginRQ.getPlugin_id() == PluginsEnum.API_QUALYS.pluginId;
    }
}
