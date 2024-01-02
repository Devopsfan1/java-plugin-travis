/*
 * IBM Confidential
 * PID 5900-B4I
 * © Copyright IBM Corp. 2023
 *
 */
package com.ibm.advisor.plugin.impl.file.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.advisor.dto.PluginRQ;
import com.ibm.advisor.plugin.common.FileBasedPlugin;
import com.ibm.advisor.plugin.intf.AdvisorPluginInterface;
import com.ibm.advisor.plugin.service.S3Service;
import com.ibm.advisor.plugin.util.PluginsEnum;
import com.ibm.dim.ScanDataDim;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JsonImportPlugin extends FileBasedPlugin implements AdvisorPluginInterface {

    static final Logger logger = LoggerFactory.getLogger(JsonImportPlugin.class);

    @Autowired
    S3Service s3Service;

    @Override
    public boolean supports(PluginRQ pluginRQ) {
        return pluginRQ.getPlugin_id() == PluginsEnum.FILE_JSON.pluginId;
    }

    @Override
    public ScanDataDim fetchScanResult(PluginRQ pluginRQ, long scanId) {
        logger.info("Running JsonImportPlugin: {} ", pluginRQ);
        ScanDataDim scanData = null;
        try {
            // fetch json from file
            String fileName = pluginRQ.getExternal_param().get("file_name");
            s3Service.connect();
            String fileContent = s3Service.getFileByName(fileName);
            // convert json to ScanData format
            ObjectMapper mapper = new ObjectMapper();
            scanData = mapper.readValue(fileContent, ScanDataDim.class);
            // validate pojo against to schema

			/*ValidationResult validationResult = validateScanData(scanData,"FILE");
			scanData.setValidationResult(validationResult);*/

        } catch (Exception e) {
            e.printStackTrace();
        }
        return scanData;
    }

}
