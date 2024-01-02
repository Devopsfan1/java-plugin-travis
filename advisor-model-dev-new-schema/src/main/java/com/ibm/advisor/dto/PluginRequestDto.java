/*******************************************************************************
 *  * IBM Confidential
 *  * PID 5900-B4I
 *  * © Copyright IBM Corp. 2023
 *
 *  
 *******************************************************************************/
package com.ibm.advisor.dto;

import java.sql.Timestamp;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class PluginRequestDto {

	private long plugin_id;
	@NotBlank(message = "The Plugin name is required.")
	private String plugin_name;
	private String plugin_desc;
	@NotBlank(message = "The Plugin type is required.")
	private String plugin_type;
	private Timestamp creation_ts;
			
}
