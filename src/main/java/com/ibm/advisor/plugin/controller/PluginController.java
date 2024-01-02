/*
 * IBM Confidential
 * PID 5900-B4I
 * © Copyright IBM Corp. 2023
 *
 */
package com.ibm.advisor.plugin.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ibm.advisor.dto.PluginRQ;
import com.ibm.advisor.dto.PluginRS;
import com.ibm.advisor.plugin.error.InvalidInputException;
import com.ibm.advisor.plugin.error.PluginNotFoundException;
import com.ibm.advisor.plugin.intf.AdvisorPluginInterface;
import com.ibm.advisor.plugin.service.PluginService;
import com.ibm.advisor.plugin.util.Utility;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.plugin.core.PluginRegistry;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/plugins")
@Tag(name = "Plugins API")
public class PluginController {

	static final Logger logger = LoggerFactory.getLogger(PluginController.class);
	@Autowired
	PluginRegistry<AdvisorPluginInterface, PluginRQ> plugins;
	@Autowired
	private PluginService pluginService;

	@PostMapping("/invokePlugin")
	@Operation(summary = "Invokes specific plugin identified by plugin_id", description = "Plugin id list: QUALYS_API = 1 , NESSUS_API = 2 , CMDB_API = 3 , FILE_JSON = 4")
	public ResponseEntity<Object> invokePlugin(@RequestHeader(value = "X-OrgId", required = true) String orgId, @RequestBody @Valid PluginRQ pluginRQ) throws JsonProcessingException {
		pluginRQ.setOrg_id(Long.parseLong(orgId));
		logger.info("PluginRQ : {} ", pluginRQ);
		if (pluginRQ.getPlugin_id().longValue() == 1) {
			//QUALYS_API
			if (!pluginRQ.getExternal_param().containsKey("api_url")) {
				throw new InvalidInputException("api_url not found.");
			}
			if (pluginRQ.getExternal_param().containsKey("api_url")) {
				if (!Utility.isValidUrl(pluginRQ.getExternal_param().get("api_url"))) {
					throw new InvalidInputException("Invalid api_url provided.");
				}
			}
			if (!pluginRQ.getExternal_param().containsKey("api_username")) {
				throw new InvalidInputException("api_username not found.");
			}
			if (!pluginRQ.getExternal_param().containsKey("api_password")) {
				throw new InvalidInputException("api_password not found.");
			}
			if (!pluginRQ.getExternal_param().containsKey("scan_search_id")) {
				throw new InvalidInputException("scan_search_id not found.");
			}
		}
		if (pluginRQ.getPlugin_id().longValue() == 4) {
			//FILE IMPORT
			if (!pluginRQ.getExternal_param().containsKey("file_name")) {
				throw new InvalidInputException("file_name not found.");
			}
		}
		Optional<AdvisorPluginInterface> pluginOpt = plugins.getPluginFor(pluginRQ);

		if (pluginOpt.isPresent()) {
			AdvisorPluginInterface pluginIntf = pluginOpt.get();
			PluginRS scanRegRs = pluginService.registerScan(pluginRQ);
			long scanId = scanRegRs.getScan_id();
			logger.info("Scan ID: {}", scanId);

			CompletableFuture<PluginRS> scanData = pluginService.fetchScanAndSave(pluginRQ, pluginIntf, scanId);

			PluginRS asyncResp = PluginRS.builder().org_id(pluginRQ.getOrg_id())
					.user_email(pluginRQ.getUser_email())
					.plugin_id(pluginRQ.getPlugin_id())
					.scan_id(scanId)
					.scan_desc(scanRegRs.getScan_desc())
					.build();


			return new ResponseEntity<>(asyncResp, HttpStatus.OK);
		} else {
			throw new PluginNotFoundException("Plugin Not Found");
		}
	}

}
