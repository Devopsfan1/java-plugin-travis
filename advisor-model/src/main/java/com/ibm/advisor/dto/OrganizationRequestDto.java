/*******************************************************************************
 *  * IBM Confidential
 *  * PID 5900-B4I
 *  * © Copyright IBM Corp. 2023
 *
 *  
 *******************************************************************************/
package com.ibm.advisor.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class OrganizationRequestDto {
	private long org_id;
	@NotBlank(message = "The Organization name is required.")
	private String org_name;
	private String org_desc;
	private long org_owner_user_id;
}
