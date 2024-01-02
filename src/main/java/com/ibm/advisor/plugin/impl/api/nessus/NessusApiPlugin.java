/*
 * IBM Confidential
 * PID 5900-B4I
 * © Copyright IBM Corp. 2023
 *
 */
package com.ibm.advisor.plugin.impl.api.nessus;

import com.ibm.dim.ScanDataDim;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.ibm.advisor.dto.PluginRQ;
import com.ibm.advisor.dto.ScanData;
import com.ibm.advisor.plugin.common.NetworkScanApiPlugin;
import com.ibm.advisor.plugin.intf.AdvisorPluginInterface;
import com.ibm.advisor.plugin.util.PluginsEnum;

@Component
public class NessusApiPlugin extends NetworkScanApiPlugin implements AdvisorPluginInterface{
	static final Logger logger = LoggerFactory.getLogger(NessusApiPlugin.class);
	@Override
	public boolean supports(PluginRQ pluginRQ) {
		return pluginRQ.getPlugin_id()==PluginsEnum.API_NESSUS.pluginId;
	}

	@Override
	public ScanDataDim fetchScanResult(PluginRQ pluginRQ, long scanId) {
		logger.info("Running NessusApiPlugin: {} ", pluginRQ);
		return null;
	}

}
