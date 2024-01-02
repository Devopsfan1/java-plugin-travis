/*
 * IBM Confidential
 * PID 5900-B4I
 * © Copyright IBM Corp. 2023
 *
 */
package com.ibm.advisor.plugin.common;

import com.ibm.advisor.dto.MasterData;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AdvisorPluginClass {

    @Autowired
    private MasterData masterData;


	/*public ValidationResult validateScanData(ScanData scanData, String type) throws InvalidParameterException {
		if ("API".equals(type)) {
			return validator.validateSchemaApi(masterData,scanData);
		}else if("FILE".equals(type)) {
			return validator.validateSchemaFile(masterData,scanData);
		} else {
			throw new InvalidParameterException("Invalid source type for validation");
		}
		
	}*/
}
