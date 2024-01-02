/*
 * IBM Confidential
 * PID 5900-B4I 
 * Copyright (c) IBM Corp. 2023 
 */
package com.ibm.advisor.dto;

import java.util.Map;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data @ToString @Builder
public class ValidationResult {
	private boolean valid;
	private Map<String,String> errors;

}
