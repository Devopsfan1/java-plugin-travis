/*
 * IBM Confidential
 * PID 5900-B4I
 * © Copyright IBM Corp. 2023
 *
 */
package com.ibm.advisor.dto;

import com.ibm.dim.EntityNewSchema;
import lombok.*;

import java.util.List;


@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class PluginRS {
	private long org_id;
	private long plugin_id;
	private String user_email;
	private long scan_id;
	private String scan_desc;
	private ValidationResult validationResult;
	private List<EntityNewSchema> dims;
	private List<EntityNewSchema> facts;
}
