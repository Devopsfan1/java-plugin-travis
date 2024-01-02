/*
 * IBM Confidential
 * PID 5900-B4I
 * © Copyright IBM Corp. 2023
 *
 */
package com.ibm.advisor.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

 
public class ScanData {
	
	private ValidationResult validationResult;
	
	private LocalDate scanDate;
	
	private List<Object> entities;
	
	private List<Object> relationships;

	public ValidationResult getValidationResult() {
		return validationResult;
	}

	public void setValidationResult(ValidationResult validationResult) {
		this.validationResult = validationResult;
	}
	
	public LocalDate getScanDate() {
		return scanDate;
	}

	public void setScanDate(LocalDate scanDate) {
		this.scanDate = scanDate;
	}

	public List<Object> getEntities() {
		if (entities==null) {
			entities = new ArrayList<>();
		}
		return entities;
	}

	public void setEntities(List<Object> entities) {
		this.entities = entities;
	}

	public List<Object> getRelationships() {
		if (relationships==null) {
			relationships = new ArrayList<>();
		}
		return relationships;
	}

	public void setRelationships(List<Object> relationships) {
		this.relationships = relationships;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ScanData [validationResult=");
		builder.append(validationResult);
		builder.append(", scanDate=");
		builder.append(scanDate);
		builder.append(", entities=");
		builder.append(entities);
		builder.append(", relationships=");
		builder.append(relationships);
		builder.append("]");
		return builder.toString();
	}

}
