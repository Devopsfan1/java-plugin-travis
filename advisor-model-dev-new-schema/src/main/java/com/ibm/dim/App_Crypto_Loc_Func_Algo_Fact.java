/*
 * IBM Confidential
 * PID 5900-B4I 
 * Copyright (c) IBM Corp. 2023 
 */
package com.ibm.dim;

import lombok.*;

import java.util.UUID;

@Data @AllArgsConstructor @NoArgsConstructor @ToString @Builder
public class App_Crypto_Loc_Func_Algo_Fact extends EntityNewSchema{
	private UUID app_uuid;
	private UUID location_uuid;
	private UUID cryptofunc_uuid;
	private UUID cryptoalgo_uuid;
}
