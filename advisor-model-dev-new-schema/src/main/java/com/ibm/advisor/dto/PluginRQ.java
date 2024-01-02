/*
 * IBM Confidential
 * PID 5900-B4I
 * © Copyright IBM Corp. 2023
 *
 */
package com.ibm.advisor.dto;

import java.util.Map;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder @ToString
public class PluginRQ {
	
	private Long org_id;
	@Min(value = 1 , message = "plugin_id can not be less than one.")
	private Long plugin_id;
	private String user_email;
	private String scan_desc;
	private Map<String,String> external_param;

}
