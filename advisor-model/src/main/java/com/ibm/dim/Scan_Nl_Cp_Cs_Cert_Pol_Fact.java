/*
 * IBM Confidential
 * PID 5900-B4I 
 * Copyright (c) IBM Corp. 2023 
 */
package com.ibm.dim;

import lombok.*;

import java.util.UUID;

@Data @AllArgsConstructor @NoArgsConstructor @ToString @Builder
public class Scan_Nl_Cp_Cs_Cert_Pol_Fact extends EntityNewSchema{
	private Integer scan_id; 
	private UUID netloc_uuid;
	private UUID protocol_uuid;
	private UUID ciphersuite_uuid;
	private UUID cert_uuid;
	private UUID policy_uuid;
	private Integer nl_pol_status;
	private Integer cp_pol_status; 
	private Integer cs_pol_status; 
	private Integer cert_pol_status; 
}
