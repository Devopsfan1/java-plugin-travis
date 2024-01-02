/*
 * IBM Confidential
 * PID 5900-B4I 
 * Copyright (c) IBM Corp. 2023 
 */
package com.ibm.advisor.plugin.service;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.ibm.advisor.dto.MasterDataModel;
import com.ibm.advisor.dto.MasterResponseDto;

@Service
public class BootstrapService {
	
	static final Logger logger = LoggerFactory.getLogger(BootstrapService.class);

	@Autowired
	private RestTemplate restTemplate;

	@Value("${advisor.metadata.reader.service.url}")
	private String metadataReaderServiceUrl;

	public List<MasterDataModel> fetchMasterDataFromReaderService(){
		List<MasterDataModel> result = new ArrayList<>();
		try {
			ResponseEntity<MasterResponseDto> masterDataResp = restTemplate.getForEntity(metadataReaderServiceUrl+"/api/getMasterdata",MasterResponseDto.class);
			if (masterDataResp.hasBody() &&  masterDataResp.getBody() != null ) {
				var body = masterDataResp.getBody();
				if (body!= null && body.getMasterdata()!= null) {
					result = body.getMasterdata();
				}
			}
		} catch (Exception e) {
			logger.error("Master data not loaded because : "+e.getMessage());
		}
		return result;
	}

}
