/*
 * IBM Confidential
 * PID 5900-B4I
 * © Copyright IBM Corp. 2023
 *
 */
package com.ibm.advisor.dto;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder @ToString
public class ScanResponseDto {

	
	private long scan_id;
	private long plugin_id;
	private String user_email;
	private String scan_desc;
	private String plugin_name;
	private String plugin_type;
	private Timestamp updation_ts;
	
	
			
}
