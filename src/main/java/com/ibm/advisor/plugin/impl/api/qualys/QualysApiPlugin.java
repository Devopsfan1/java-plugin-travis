/*
 * IBM Confidential
 * PID 5900-B4I
 * © Copyright IBM Corp. 2023
 *
 */
package com.ibm.advisor.plugin.impl.api.qualys;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.advisor.dto.PluginRQ;
import com.ibm.advisor.dto.ScanData;
import com.ibm.advisor.plugin.common.NetworkScanApiPlugin;
import com.ibm.advisor.plugin.intf.AdvisorPluginInterface;
import com.ibm.advisor.plugin.util.Utility;
import com.ibm.dim.ScanDataDim;
import com.ibm.pathfinder.model.crypto.v1_0_0.*;
import com.ibm.pathfinder.model.dataobs.v1_1_0.Domain;
import com.ibm.pathfinder.model.dataobs.v1_1_0.NetLocation;
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
public class QualysApiPlugin extends NetworkScanApiPlugin implements AdvisorPluginInterface {

    static final Logger logger = LoggerFactory.getLogger(QualysApiPlugin.class);
    static final String ACTION = "action";
    static final String API_URL = "api_url";
    static HttpClientContext context = HttpClientContext.create();
    static Header commonHdr = new BasicHeader("X-Requested-With", "Facklers PyQual python primer");

    @Override
    public ScanDataDim fetchScanResult(PluginRQ pluginRQ, long scanId) {
        logger.info("Running QualysApiPlugin: {} ", pluginRQ);
        ScanData scanData = new ScanData();
        try {
            connectQualys(pluginRQ.getExternal_param());
            String dataStr = getQualysDetails(pluginRQ.getExternal_param());
            logger.info("dataStr : {}", dataStr);
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            QualysDataModel[] qualysDataModelList = mapper.readValue(dataStr, QualysDataModel[].class);
            for (QualysDataModel qualysDataModel : qualysDataModelList) {
                if (qualysDataModel.getLaunch_date() != null) {
                    scanData.setScanDate(Utility.toLocalDate(qualysDataModel.getLaunch_date(), "M/d/yyyy HH:mm:ss"));
                }
                if (qualysDataModel.getIp() != null) {
                    parseAllElements(scanData, qualysDataModel);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                disconnectQualys(pluginRQ.getExternal_param());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
		/*// validate pojo against to schema
		try {
			ValidationResult validationResult = validateScanData(scanData,"API");
			scanData.setValidationResult(validationResult);
		} catch (Exception e) {
			logger.error("Schema validation exception : {} ", e.getMessage());
		}*/

        return null;
    }

    private void parseAllElements(ScanData scanData, QualysDataModel qualysDataModel) {
        long qid = qualysDataModel.getQid();
        if (qid == 86002) {
            parseQid_86002_Info(scanData, qualysDataModel);
        }
        if (qid == 38116) {
            parseQid_38116_Info(scanData, qualysDataModel);
        }
    }

    private void parseQid_38116_Info(ScanData scanData, QualysDataModel qualysDataModel) {
        String ip = qualysDataModel.getIp();
        String dns = qualysDataModel.getDns();
        String port = qualysDataModel.getPort();
        String results = qualysDataModel.getResults();
        //netIp start

        UUID netIpEdfId = UUID.randomUUID();
        NetLocation netIp = new NetLocation();
        netIp.setEdfId(netIpEdfId);
        netIp.setHost(ip);
        if (port != null && !"N/A".equalsIgnoreCase(port)) {
            netIp.setPort(Integer.parseInt(port));
        }
        //Adding to main return Object scanData
        scanData.getEntities().add(netIp);
        //netIp end


        UUID domainEdfId = netIpEdfId;
        if (dns != null) {
            //Domain start
            domainEdfId = UUID.randomUUID();
            Domain domain = new Domain();
            domain.setEdfId(domainEdfId);
            domain.setDns(dns);
            //Adding to main return Object scanData
            scanData.getEntities().add(domain);
            //Domain end

            Has rel3 = new Has();
            rel3.setFromEdfId(netIpEdfId);
            rel3.setToEdfId(domainEdfId);
            scanData.getRelationships().add(rel3);
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
                    Cryptographicprotocol cryptoprotocol = new Cryptographicprotocol();
                    cryptoprotocol.setEdfId(cryptographicProtocolEdfId);
                    cryptoprotocol.setName(valArr[0]);
                    cryptoprotocol.setType(protocolType);
                    cryptoprotocol.setVersion(version);
                    //Adding to main return Object scanData
                    scanData.getEntities().add(cryptoprotocol);

                    Uses rel4 = new Uses();
                    rel4.setFromEdfId(domainEdfId);
                    rel4.setToEdfId(cryptographicProtocolEdfId);
                    scanData.getRelationships().add(rel4);

                }
            } else {
                UUID ciphersuiteEdfId = UUID.randomUUID();
                Ciphersuite ciphersuite = new Ciphersuite();
                ciphersuite.setEdfId(ciphersuiteEdfId);
                ciphersuite.setName(valArr[0]);
                ciphersuite.setSecurity(valArr[5]);
                scanData.getEntities().add(ciphersuite);

                Uses rel5 = new Uses();
                rel5.setFromEdfId(cryptographicProtocolEdfId);
                rel5.setToEdfId(ciphersuiteEdfId);
                scanData.getRelationships().add(rel5);

                UUID hashEdfId = UUID.randomUUID();
                Hash hash = new Hash();
                hash.setEdfId(hashEdfId);
                hash.setAlgorithm(valArr[0] + ":" + valArr[3]);

                scanData.getEntities().add(hash);

                Uses rel6 = new Uses();
                rel6.setFromEdfId(ciphersuiteEdfId);
                rel6.setToEdfId(hashEdfId);
                scanData.getRelationships().add(rel6);

                /*
                 * IBM Confidential
                 * PID 5900-B4I
                 * © Copyright IBM Corp. 2023
                 *
                 */


                UUID pkencryptionEdfId = UUID.randomUUID();
                Pkencryption pkEncryption = new Pkencryption();
                pkEncryption.setEdfId(pkencryptionEdfId);
                pkEncryption.setAlgorithm(valArr[0] + ":" + valArr[4]);
                pkEncryption.setKeyLength(Long.parseLong(valArr[4].substring(valArr[4].indexOf("(") + 1, valArr[4].indexOf(")"))));


                scanData.getEntities().add(pkEncryption);

                Uses rel7 = new Uses();
                rel7.setFromEdfId(ciphersuiteEdfId);
                rel7.setToEdfId(pkencryptionEdfId);
                scanData.getRelationships().add(rel7);
            }
        }
    }

    private void parseQid_86002_Info(ScanData scanData, QualysDataModel qualysDataModel) {
        String ip = qualysDataModel.getIp();
        String dns = qualysDataModel.getDns();
        String port = qualysDataModel.getPort();
        String results = qualysDataModel.getResults();
        // parse certificate info
        //netIp start
        UUID netIpEdfId = UUID.randomUUID();
        NetLocation netIp = new NetLocation();
        netIp.setEdfId(netIpEdfId);
        netIp.setHost(ip);
        netIp.setHost(ip);
        if (port != null && !"N/A".equalsIgnoreCase(port)) {
            netIp.setPort(Integer.parseInt(port));
        }
        //Adding to main return Object scanData
        scanData.getEntities().add(netIp);
        //netIp end

        UUID domainEdfId = netIpEdfId;
        if (dns != null) {
            //Domain start
            domainEdfId = UUID.randomUUID();
            Domain domain = new Domain();
            domain.setEdfId(domainEdfId);
            domain.setDns(dns);
            //Adding to main return Object scanData
            scanData.getEntities().add(domain);
            //NetLocation end

            Has rel1 = new Has();
            rel1.setFromEdfId(netIpEdfId);
            rel1.setToEdfId(domainEdfId);
            scanData.getRelationships().add(rel1);
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
        Certificate cert = new Certificate();
        cert.setEdfId(certificateEdfId);
        String issuer = certMap.get("ISSUER NAME");
        if (issuer == null || issuer.isEmpty()) {
            issuer = "N/A";
        }
        cert.setIssuer(issuer);
        cert.setSignatureAlgo(certMap.get("Signature Algorithm"));
        cert.setAlgorithm(certMap.get("Public Key Algorithm"));
        cert.setNotAfter(Utility.toLocalDate(certMap.get("Valid From").substring(0, certMap.get("Valid From").length() - 4), "MMM d HH:mm:ss yyyy"));
        cert.setNotBefore(Utility.toLocalDate(certMap.get("Valid Till").substring(0, certMap.get("Valid Till").length() - 4), "MMM d HH:mm:ss yyyy"));
        cert.setSubject(certMap.get("SUBJECT NAME"));
        String certVer = certMap.get("Version");
        if (certVer != null && certVer.contains("(")) {
            certVer = certVer.split("[(]")[0].trim();
        }
        cert.setVersion(certVer);
        //Adding to main return Object scanData
        scanData.getEntities().add(cert);

        Uses rel2 = new Uses();
        rel2.setFromEdfId(domainEdfId);
        rel2.setToEdfId(certificateEdfId);
        scanData.getRelationships().add(rel2);

    }

    @Override
    public boolean supports(PluginRQ pluginRQ) {
        return false;
    }


    private void connectQualys(Map<String, String> map) throws IOException {
        HttpPost post = new HttpPost(map.get(API_URL) + "session/");
        // add request parameters or form parameters
        List<NameValuePair> urlParameters = new ArrayList<>();
        urlParameters.add(new BasicNameValuePair(ACTION, "login"));
        urlParameters.add(new BasicNameValuePair("username", map.get("api_username")));
        urlParameters.add(new BasicNameValuePair("password", map.get("api_password")));
        post.setEntity(new UrlEncodedFormEntity(urlParameters));
        post.addHeader(commonHdr);
        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(post, context)) {
            Object result = EntityUtils.toString(response.getEntity());
            logger.info("Qualys Loging Response : {}", result);
        }
    }

    private String getQualysDetails(Map<String, String> map) throws IOException {
        HttpPost post = new HttpPost(map.get(API_URL) + "scan/");
        List<NameValuePair> urlParameters = new ArrayList<>();
        urlParameters.add(new BasicNameValuePair(ACTION, "fetch"));
        urlParameters.add(new BasicNameValuePair("scan_ref", map.get("scan_search_id")));
        urlParameters.add(new BasicNameValuePair("output_format", "json_extended"));
        urlParameters.add(new BasicNameValuePair("mode", "extended"));
        post.setEntity(new UrlEncodedFormEntity(urlParameters));
        post.addHeader(commonHdr);
        try (CloseableHttpClient posthttpClient = HttpClients.createDefault();
             CloseableHttpResponse postResponse = posthttpClient.execute(post, context)) {
            String postResult = EntityUtils.toString(postResponse.getEntity());
            return postResult;
        }
    }

    private void disconnectQualys(Map<String, String> map) throws IOException {
        String logout_url = map.get(API_URL) + "session/?action=logout";
        HttpPost post = new HttpPost(logout_url);
        post.addHeader(commonHdr);
        List<NameValuePair> urlParameters = new ArrayList<>();
        urlParameters.add(new BasicNameValuePair(ACTION, "logout"));
        post.setEntity(new UrlEncodedFormEntity(urlParameters));
        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(post, context)) {
            String result = EntityUtils.toString(response.getEntity());
            logger.info("Qualys Logout Response : {}", result);
        }

    }
}
