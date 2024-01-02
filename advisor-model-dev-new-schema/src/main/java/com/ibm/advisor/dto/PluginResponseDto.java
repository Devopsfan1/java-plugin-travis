/*******************************************************************************
 *  * IBM Confidential
 *  * PID 5900-B4I
 *  * © Copyright IBM Corp. 2023
 *
 *  
 *******************************************************************************/
package com.ibm.advisor.dto;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class PluginResponseDto {

	private long plugin_id;
	private String plugin_name;
	private String plugin_desc;
	private String plugin_type;
	private Timestamp creation_ts;
	private Timestamp updation_ts;
			
}
