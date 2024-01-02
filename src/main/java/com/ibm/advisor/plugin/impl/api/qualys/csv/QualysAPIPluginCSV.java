package com.ibm.advisor.plugin.impl.api.qualys.csv;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.http.Header;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.message.BasicHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ibm.advisor.dto.PluginRQ;
import com.ibm.advisor.plugin.common.FileBasedPlugin;
import com.ibm.advisor.plugin.intf.AdvisorPluginInterface;
import com.ibm.advisor.plugin.service.S3Service;
import com.ibm.advisor.plugin.util.PluginsEnum;
import com.ibm.advisor.plugin.util.Utility;
import com.ibm.dim.Cert_Dim;
import com.ibm.dim.Ciphersuite_Dim;
import com.ibm.dim.Netloc_Dim;
import com.ibm.dim.Protocol_Dim;
import com.ibm.dim.ScanDataDim;
import com.ibm.dim.Scan_Nl_Cert_Fact;
import com.ibm.dim.Scan_Nl_Cp_Cs_Fact;

@Component
public class QualysAPIPluginCSV extends FileBasedPlugin implements AdvisorPluginInterface {

    static final Logger log = LoggerFactory.getLogger(QualysAPIPluginCSV.class);
    private static final int QUALYS_QID_86002 = 86002;
    private static final int QUALYS_QID_38116 = 38116;
    HashMap<String, Netloc_Dim> netlocGlobalMap = new HashMap<>();
    List<Map<String, String>> errorRecords = new ArrayList<Map<String,String>>();
    
    @Autowired
    S3Service s3Service;

    @Override
    public ScanDataDim fetchScanResult(PluginRQ pluginRQ, long scanId) {
        log.info("Running Qualys API Plugin: {} ", pluginRQ);
        ScanDataDim scanDataDim = new ScanDataDim();
        try {
            // fetch json from file
            /*String fileName = pluginRQ.getExternal_param().get("file_name");
            s3Service.connect();*/
            String fileContent = Utility.readFileAsString("C:\\Users\\002KV5744\\Downloads\\Scan_Report_RM_Quantum_20231208_natnw_rm2_20231208.csv\\Scan_Report_NBS_2023-12-09_SMALL.csv");//s3Service.getFileByName(fileName);
            //log.info("fileContent {} : ", fileContent);
            parseQualysData(scanDataDim, fileContent, scanId);
            Utility.writeStringToFile(Utility.convertToJsonString(errorRecords),"C:\\Users\\002KV5744\\Downloads\\Scan_Report_RM_Quantum_20231208_natnw_rm2_20231208.csv\\", "ERROR.json");
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Error during Qualys API Plugin execution: {}", e.getMessage());
            System.exit(1);
        }
        return scanDataDim;
    }

    private void parseQualysData(ScanDataDim scanDataDim, String dataStr, long scanId) throws IOException {
    	List<Map<String, String>> csvRowList = Utility.readCSVStringContentToMap(dataStr);
    	log.info("csvRowList count {} : ", csvRowList.size());
        for (Map<String, String> qualysDataMap : csvRowList) {
            if (qualysDataMap.containsKey("IP") && qualysDataMap.get("IP") != null) {
                parseAllElements(scanDataDim, qualysDataMap, scanId);
            }
        }
    }

    private void parseAllElements(ScanDataDim scanDataDim, Map<String, String> qualysDataMap, long scanId) {
        int qid = Integer.parseInt(qualysDataMap.get("QID").trim());
        if (qid == QUALYS_QID_86002) {
            log.info("Parsing 86002 qualys Data Model");
            parseQid_86002_Info(scanDataDim, qualysDataMap, scanId);

        }
        if (qid == QUALYS_QID_38116) {
            log.info("Parsing 38116 qualys Data Model");
            parseQid_38116_Info(scanDataDim, qualysDataMap, scanId);
        }
    }

    private void parseQid_86002_Info(ScanDataDim scanData, Map<String, String> qualysDataMap, long scanId) {
    	try {
    		String ip = qualysDataMap.get("IP");
            String dns = qualysDataMap.get("DNS");
            String port = qualysDataMap.get("Port");
            String results = qualysDataMap.get("Results");
            Netloc_Dim netLocDim=null;
    		if (netlocGlobalMap.containsKey(ip+"#"+port+"#"+dns)) {
    			netLocDim = netlocGlobalMap.get(ip+"#"+port+"#"+dns);
    		} else {
    	        UUID netLocUUID = UUID.randomUUID();
    	        netLocDim = new Netloc_Dim();
    	        netLocDim.setNetloc_uuid(netLocUUID);
    	        netLocDim.setNetloc_host(ip);
    	        netLocDim.setNetloc_dns(dns);
    	        if (port != null && !"N/A".equalsIgnoreCase(port)) {
    	            netLocDim.setNetloc_port(port);
    	        }
    	        netlocGlobalMap.put(ip+"#"+port+"#"+dns, netLocDim);
    	        scanData.getDims().add(netLocDim);
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
            scanNlCertFact.setNetloc_uuid(netLocDim.getNetloc_uuid());

            
            scanData.getDims().add(certDim);
            scanData.getFacts().add(scanNlCertFact);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("qualysDataMap {} ",qualysDataMap);
			errorRecords.add(qualysDataMap);
		}
        
    }

    private void parseQid_38116_Info(ScanDataDim scanData, Map<String, String> qualysDataMap, long scanId) {
    	try {
    		String ip = qualysDataMap.get("IP");
            String dns = qualysDataMap.get("DNS");
            String port = qualysDataMap.get("Port");
            String results = qualysDataMap.get("Results");

            Netloc_Dim netLocDim=null;
    		if (netlocGlobalMap.containsKey(ip+"#"+port+"#"+dns)) {
    			netLocDim = netlocGlobalMap.get(ip+"#"+port+"#"+dns);
    		} else {
    	        UUID netLocUUID = UUID.randomUUID();
    	        netLocDim = new Netloc_Dim();
    	        netLocDim.setNetloc_uuid(netLocUUID);
    	        netLocDim.setNetloc_host(ip);
    	        netLocDim.setNetloc_dns(dns);
    	        if (port != null && !"N/A".equalsIgnoreCase(port)) {
    	            netLocDim.setNetloc_port(port);
    	        }
    	        netlocGlobalMap.put(ip+"#"+port+"#"+dns, netLocDim);
    	        scanData.getDims().add(netLocDim);
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
                    scanNlCpCsFact.setNetloc_uuid(netLocDim.getNetloc_uuid());
                    scanNlCpCsFact.setProtocol_uuid(cryptographicProtocolEdfId);
                    scanNlCpCsFact.setScan_id((int) scanId);

                    scanData.getDims().add(cipherSuiteDim);
                    scanData.getFacts().add(scanNlCpCsFact);
                }
            }
    	}catch (Exception e) {
			e.printStackTrace();
			log.info("qualysDataMap {} ",qualysDataMap);
			errorRecords.add(qualysDataMap);
		}
    }

    @Override
    public boolean supports(PluginRQ pluginRQ) {
        return pluginRQ.getPlugin_id() == PluginsEnum.FILE_QUALYS_CSV.pluginId;
    }
}
