/*******************************************************************************
 *  * IBM Confidential
 *  * PID 5900-B4I
 *  * © Copyright IBM Corp. 2023
 *
 *  
 *******************************************************************************/
package com.ibm.advisor.dto;

import java.util.List;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class MasterResponseDto {
	private List<MasterDataModel> masterdata;
}
