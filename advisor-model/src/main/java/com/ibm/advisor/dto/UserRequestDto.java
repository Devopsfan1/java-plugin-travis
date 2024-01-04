/*******************************************************************************
 *  * IBM Confidential
 *  * PID 5900-B4I
 *  * © Copyright IBM Corp. 2023
 *
 *  
 *******************************************************************************/
package com.ibm.advisor.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class UserRequestDto {
	private long user_id;
	private String role;
	private Long role_id;
	private String email;
	private String login_id;
	private String userid;
}
