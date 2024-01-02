/*
 * IBM Confidential
 * PID 5900-B4I 
 * Copyright (c) IBM Corp. 2023 
 */
package com.ibm.dim;

import lombok.*;

import java.util.UUID;

@Data @AllArgsConstructor @NoArgsConstructor @ToString @Builder
public class Nl_App_Prov_Ep_Fact extends EntityNewSchema{
	private UUID netloc_uuid;
	private UUID app_uuid;
	private UUID provision_uuid;
	private UUID endpoint_uuid;
}
