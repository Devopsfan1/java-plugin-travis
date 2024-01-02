/*
 * IBM Confidential
 * PID 5900-B4I
 * Copyright (c) IBM Corp. 2023
 */
package com.ibm.advisor.plugin;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AdvisorPluginBeans {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
	
	/*@Bean
	public Validator getValidator() {
		return new Validator();
	}*/
}
