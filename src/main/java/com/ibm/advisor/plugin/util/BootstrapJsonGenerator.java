/*
 * IBM Confidential
 * PID 5900-B4I 
 * Copyright (c) IBM Corp. 2023 
 */
package com.ibm.advisor.plugin.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.ibm.advisor.dto.MasterDataModel;
import com.ibm.advisor.dto.MasterResponseDto;

public class BootstrapJsonGenerator {
	
	private static String metadataReaderServiceUrl = "https://reader-advisor-dev.q-safe-advisor-f9f6c195465e0b0fcac01274faf6129a-0000.eu-de.containers.appdomain.cloud";

	public static void main(String[] args) {
		List<MasterDataModel> masterObjList = fetchMasterDataFromReaderService();
		String jsonString = Utility.convertToJsonString(masterObjList);
		Utility.writeStringToFile(jsonString, "C:\\Users\\002KV5744\\QSWorks\\advisor-data-extractor\\src\\main\\resources", "BootstrapJson.json",false);
		
	}
	
	public static List<MasterDataModel> fetchMasterDataFromReaderService(){
		List<MasterDataModel> result = new ArrayList<>();
		try {
			ResponseEntity<MasterResponseDto> masterDataResp = new RestTemplate().getForEntity(metadataReaderServiceUrl+"/api/getMasterdata",MasterResponseDto.class);
			if (masterDataResp.hasBody() &&  masterDataResp.getBody() != null ) {
				 var body = masterDataResp.getBody();
				 if (body!= null && body.getMasterdata()!= null) {
					 result = body.getMasterdata();
				}
			 }
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		return result;
	}

}
