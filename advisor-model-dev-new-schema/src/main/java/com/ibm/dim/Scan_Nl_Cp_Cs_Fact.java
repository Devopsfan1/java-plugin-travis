/*
 * IBM Confidential
 * PID 5900-B4I 
 * Copyright (c) IBM Corp. 2023 
 */
package com.ibm.dim;

import lombok.*;

import java.util.UUID;

@Data @AllArgsConstructor @NoArgsConstructor @ToString @Builder
public class Scan_Nl_Cp_Cs_Fact extends EntityNewSchema{
	private Integer scan_id; 
	private UUID netloc_uuid;
	private UUID protocol_uuid;
	private UUID ciphersuite_uuid;
}
