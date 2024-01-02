/*
 * IBM Confidential
 * PID 5900-B4I
 * © Copyright IBM Corp. 2023
 *
 */
package com.ibm.advisor.plugin.util;

public enum PluginsEnum {
	API_QUALYS(1),
	API_NESSUS(2),
	API_CMDB(3),
	FILE_JSON(4),
	FILE_QUALYS_CSV(5);
	
	public final int pluginId;
	
	PluginsEnum(int pluginId) {
		this.pluginId=pluginId;
	}
	
}
