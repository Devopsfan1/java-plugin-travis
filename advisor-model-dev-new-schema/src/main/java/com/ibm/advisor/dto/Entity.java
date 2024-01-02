/*
 * IBM Confidential
 * PID 5900-B4I
 * © Copyright IBM Corp. 2023
 *
 */
package com.ibm.advisor.dto;

import java.util.UUID;

import org.json.simple.JSONObject;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder @ToString
public class Entity {
	private UUID entity_uuid;
	private Long entity_type_ref_id;
	private JSONObject metadataJSON;


}