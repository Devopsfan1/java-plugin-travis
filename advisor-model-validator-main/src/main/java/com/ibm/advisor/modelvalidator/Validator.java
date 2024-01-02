/*
 * IBM Confidential
 * PID 5900-B4I 
 * Copyright (c) IBM Corp. 2023 
 */
package com.ibm.advisor.modelvalidator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.advisor.dto.MasterData;
import com.ibm.advisor.dto.MasterDataModel;
import com.ibm.advisor.dto.ScanData;
import com.ibm.advisor.dto.ValidationResult;
import com.ibm.pathfinder.model.system.v1_0_0.Entity;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;
import com.networknt.schema.ValidationMessage;

public class Validator {
	
	static final Logger logger = LoggerFactory.getLogger(Validator.class);
	
	private static ObjectMapper mapper = new ObjectMapper();  
	private static JsonSchemaFactory factory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V202012);
	 
	public Validator() {
		mapper.findAndRegisterModules();
	}

	public ValidationResult validateSchemaApi(MasterData masterData, ScanData scanData) {
		Map<String,MasterDataModel> entityMasterMap = masterData.getMasterEntity();
		Map<String,String> errorMaps = new HashMap<>();
		List<UUID> faultList = new ArrayList<>();
		scanData.getEntities().forEach(ent -> {
			try {
				MasterDataModel masterDataEntity=null;
				String entityClassName = ent.getClass().getSimpleName();
				if (entityMasterMap.containsKey(entityClassName)) {
					masterDataEntity = entityMasterMap.get(entityClassName);
					Entity entx =(Entity) ent;
					Set<ValidationMessage> msgs = validateOne(masterDataEntity.getRef_schema().toJSONString(),ent);
					UUID entity_uuid = entx.getEdfId();
					if (msgs.size() > 0) {
						//has error
						faultList.add(entity_uuid);
						errorMaps.put(entityClassName+"#"+entity_uuid, msgs.stream().map(ve -> ve.getMessage()).collect(Collectors.joining(":")));
						msgs.stream().forEach( ve -> {
							entx.getVerificationMessage().put(ve.getMessage().split(":")[0].split("[.]")[1], ve.getMessage().split(":")[1]);
						});
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		return ValidationResult.builder().valid(errorMaps.isEmpty()).errors(errorMaps).build();
	}
	
	@SuppressWarnings("rawtypes")
	public ValidationResult validateSchemaFile(MasterData masterData, ScanData scanData) {
		Map<Long, MasterDataModel> entityMasterMap = masterData.getMasterAllById();
		Map<String,String> errorMaps = new HashMap<>();
		List<UUID> faultList = new ArrayList<>();
		scanData.getEntities().forEach(ent -> {
			try {
				MasterDataModel masterDataEntity=null;
				Map entMap =(Map)ent;
				String jsonmetadataJSON = new ObjectMapper().writeValueAsString(entMap.get("metadataJSON"));
				long entity_type_ref_id=(int) entMap.get("entity_type_ref_id");
				UUID entity_uuid = UUID.fromString((String)entMap.get("entity_uuid"));
				if (entityMasterMap.containsKey(entity_type_ref_id)) {
					masterDataEntity = entityMasterMap.get(entity_type_ref_id);
					Set<ValidationMessage> msgs = validateOne(masterDataEntity.getRef_schema().toJSONString(),jsonmetadataJSON);
					if (msgs.size() > 0) {
						//has error
						faultList.add(entity_uuid);
						errorMaps.put(masterDataEntity.getRef_name()+"#"+entity_uuid, msgs.stream().map(ve -> ve.getMessage()).collect(Collectors.joining(":")));
						Map<String,String> newErrorMap = new HashMap<>();
						msgs.stream().forEach( ve -> {
							newErrorMap.put(ve.getMessage().split(":")[0].split("[.]")[1], ve.getMessage().split(":")[1]);
						});
						((Map) entMap.get("metadataJSON")).put("verificationMessage", newErrorMap);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		return ValidationResult.builder().valid(errorMaps.isEmpty()).errors(errorMaps).build();
	}


	private Set<ValidationMessage> validateOne(String ref_desc, Object ent) {
		Set<ValidationMessage> errors = new HashSet<>();
		try {
			JsonSchema jsonSchema = factory.getSchema(ref_desc);
		    JsonNode jsonNode = mapper.convertValue(ent, JsonNode.class);
		    errors.addAll(jsonSchema.validate(jsonNode));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return errors;
	}
	
	private Set<ValidationMessage> validateOne(String ref_desc, String dataJson) {
		Set<ValidationMessage> errors = new HashSet<>();
		try {
			JsonSchema jsonSchema = factory.getSchema(ref_desc);
		    JsonNode jsonNode = mapper.readTree(dataJson);
		    errors.addAll(jsonSchema.validate(jsonNode));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return errors;
	}
	

}
