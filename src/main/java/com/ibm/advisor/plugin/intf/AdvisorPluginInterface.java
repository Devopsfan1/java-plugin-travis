/*
 * IBM Confidential
 * PID 5900-B4I
 * © Copyright IBM Corp. 2023
 *
 */
package com.ibm.advisor.plugin.intf;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ibm.advisor.dto.PluginRQ;
import com.ibm.dim.ScanDataDim;
import org.springframework.plugin.core.Plugin;

public interface AdvisorPluginInterface extends Plugin<PluginRQ> {
    ScanDataDim fetchScanResult(PluginRQ pluginRQ, long scanId) throws JsonProcessingException;
}
