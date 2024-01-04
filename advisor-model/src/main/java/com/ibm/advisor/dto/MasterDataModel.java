/*
 * IBM Confidential
 * PID 5900-B4I
 * © Copyright IBM Corp. 2023
 *
 */
package com.ibm.advisor.dto;

import java.sql.Timestamp;

import org.json.simple.JSONObject;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class MasterDataModel {
	private Long ref_id;
	private String ref_type;
	private String ref_name;
	private String ref_desc;
	private String ref_value;
	private String ref_version;
	private Boolean ref_has_schema;
	private JSONObject ref_schema;
	private Timestamp creation_ts;
	private Timestamp updation_ts;
}
