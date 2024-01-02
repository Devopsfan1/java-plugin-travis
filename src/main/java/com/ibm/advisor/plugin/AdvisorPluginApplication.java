/*
 * IBM Confidential
 * PID 5900-B4I
 * © Copyright IBM Corp. 2023
 *
 */
package com.ibm.advisor.plugin;

import com.ibm.advisor.dto.MasterData;
import com.ibm.advisor.dto.MasterDataModel;
import com.ibm.advisor.plugin.intf.AdvisorPluginInterface;
import com.ibm.advisor.plugin.service.BootstrapService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.plugin.core.config.EnablePluginRegistries;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.List;
import java.util.concurrent.Executor;


@SpringBootApplication
@EnablePluginRegistries({AdvisorPluginInterface.class})
@EnableAsync
public class AdvisorPluginApplication {
	static final Logger log = LoggerFactory.getLogger(AdvisorPluginApplication.class);

	@Autowired
	BootstrapService bootstrapService;

	public static void main(String[] args) {
		SpringApplication.run(AdvisorPluginApplication.class, args);
	}

	@Bean
	public Executor taskExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(2);
		executor.setMaxPoolSize(2);
		executor.setQueueCapacity(500);
		executor.setThreadNamePrefix("Async-");
		executor.initialize();
		return executor;
	}


	@Bean
	public MasterData loadMasterData() {
		log.info("Loading master data");
		MasterData masterData = new MasterData();
		List<MasterDataModel> masterDataList = bootstrapService.fetchMasterDataFromReaderService();
		for (MasterDataModel masterDataModel : masterDataList) {
			masterData.getMasterAllById().put(masterDataModel.getRef_id(), masterDataModel);
			masterData.getMasterEntity().put(masterDataModel.getRef_name(), masterDataModel);
		}
		return masterData;
	}

}
