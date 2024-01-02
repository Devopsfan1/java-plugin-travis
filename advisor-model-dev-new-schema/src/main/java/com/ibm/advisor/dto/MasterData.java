/*
 * IBM Confidential
 * PID 5900-B4I
 * © Copyright IBM Corp. 2023
 *
 */
package com.ibm.advisor.dto;

import java.util.LinkedHashMap;
import java.util.Map;


public class MasterData {

	private Map<Long,MasterDataModel> masterAllById;
	private Map<String,MasterDataModel> masterEntity;

	public Map<Long, MasterDataModel> getMasterAllById() {
		if (masterAllById==null) {
			masterAllById = new LinkedHashMap<>();
		}
		return masterAllById;
	}
	public void setMasterAllById(Map<Long, MasterDataModel> masterAllById) {
		this.masterAllById = masterAllById;
	}
	public Map<String, MasterDataModel> getMasterEntity() {
		if (masterEntity==null) {
			masterEntity = new LinkedHashMap<>();
		}
		return masterEntity;
	}
	public void setMasterEntity(Map<String, MasterDataModel> masterEntity) {
		this.masterEntity = masterEntity;
	}

}
