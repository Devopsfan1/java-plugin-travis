/*
 * IBM Confidential
 * PID 5900-B4I
 * © Copyright IBM Corp. 2023
 *
 */
package com.ibm.advisor.plugin;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.plugin.core.PluginRegistry;

import com.ibm.advisor.dto.PluginRQ;
import com.ibm.advisor.plugin.impl.api.qualys.QualysApiPlugin;
import com.ibm.advisor.plugin.intf.AdvisorPluginInterface;

@SpringBootTest
class AdvisorPluginApplicationTests {

	@Autowired
	PluginRegistry<AdvisorPluginInterface, PluginRQ> plugins;
	
	@Test
	void test_QulysPlugginLoading() {
		PluginRQ pluginRQ = PluginRQ.builder().org_id(1l).user_email("fa@test.com").plugin_id(1l).build();
		AdvisorPluginInterface pluginOpt = plugins.getPluginFor(pluginRQ).get();
		
		assertInstanceOf(QualysApiPlugin.class, pluginOpt);
	}

}
