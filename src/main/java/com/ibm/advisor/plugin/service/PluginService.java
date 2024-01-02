/*
 * IBM Confidential
 * PID 5900-B4I
 * © Copyright IBM Corp. 2023
 *
 */
package com.ibm.advisor.plugin.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ibm.advisor.dto.*;
import com.ibm.advisor.plugin.intf.AdvisorPluginInterface;
import com.ibm.dim.EntityNewSchema;
import com.ibm.dim.ScanDataDim;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.concurrent.CompletableFuture;


@Service
public class PluginService {

    static final Logger logger = LoggerFactory.getLogger(PluginService.class);
    @Autowired
    MasterData masterData;
    @Autowired
    private RestTemplate restTemplate;
    @Value("${advisor.metadata.writer.service.url}")
    private String metadataWriterServiceUrl;


    @Async
    public CompletableFuture<PluginRS> fetchScanAndSave(PluginRQ pluginRQ, AdvisorPluginInterface pluginIntf, long scanId) throws JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-OrgId", String.valueOf(pluginRQ.getOrg_id()));

        ScanDataDim scanData = pluginIntf.fetchScanResult(pluginRQ, scanId);

        //Save entities
        EntityRequestDto entitySaveRq = EntityRequestDto.builder()
                .dims(scanData.getDims().stream().map(o -> createEntity(o)).toList())
                .facts(scanData.getFacts().stream().map(o -> createEntity(o)).toList()).build();
        logger.info("entitySaveRq : {} ", entitySaveRq);

        HttpEntity<EntityRequestDto> entitySaveReqEnt = new HttpEntity<>(entitySaveRq, headers);
        ResponseEntity<String> entitySaveResp = restTemplate.postForEntity(metadataWriterServiceUrl + "/writer/insert", entitySaveReqEnt, String.class);
        logger.info("entitySaveResp {} : ", entitySaveResp);

        return CompletableFuture.completedFuture(PluginRS.builder().org_id(pluginRQ.getOrg_id())
                .user_email(pluginRQ.getUser_email())
                .plugin_id(pluginRQ.getPlugin_id())
                .scan_id(scanId)
                .dims(entitySaveRq.getDims())
                .facts(entitySaveRq.getFacts())
                .build());
    }

    @SuppressWarnings("rawtypes")
    public EntityNewSchema createEntity(Object object) {

        Map<String, MasterDataModel> entityMasterMap = masterData.getMasterEntity();
        String entityClassName = object.getClass().getSimpleName();
        MasterDataModel masterDataEntity = null;
        if (entityMasterMap.containsKey(entityClassName)) {
            masterDataEntity = entityMasterMap.get(entityClassName);
            long entity_type_ref_id = masterDataEntity.getRef_id();
            EntityNewSchema entityNewSchema = (EntityNewSchema) object;
            entityNewSchema.setRef_id((int) entity_type_ref_id);
            return entityNewSchema;
        }
        return null;
    }

    public PluginRS registerScan(@Valid PluginRQ pluginRQ) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-OrgId", String.valueOf(pluginRQ.getOrg_id()));
        //Create scan
        ScanRequestDto scanCreateRq = ScanRequestDto.builder()
                .plugin_id(pluginRQ.getPlugin_id())
                .scan_desc(pluginRQ.getScan_desc())
                .user_email(pluginRQ.getUser_email())
                .build();

        HttpEntity<ScanRequestDto> scanSaveReqEnt = new HttpEntity<>(scanCreateRq, headers);
        logger.info("scanCreateRq : {} ", scanCreateRq);
        ResponseEntity<ScanResponseDto> scanSaveResp = restTemplate.postForEntity(metadataWriterServiceUrl + "/writer/registerscan", scanSaveReqEnt, ScanResponseDto.class);
        logger.info("scanSaveResp : {} ", scanSaveResp.getBody());
        var regScanRespBody = scanSaveResp.getBody();
        return PluginRS.builder().scan_id(regScanRespBody == null ? 0 : regScanRespBody.getScan_id())
                .scan_desc(regScanRespBody == null ? "" : regScanRespBody.getScan_desc()).build();
    }

}
