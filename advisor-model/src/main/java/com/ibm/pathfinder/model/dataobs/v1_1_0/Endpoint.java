/*
 * IBM Confidential
 * PID 5900-B4I 
 * Copyright (c) IBM Corp. 2023 
 */
package com.ibm.pathfinder.model.dataobs.v1_1_0;

import java.io.Serial;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.ibm.pathfinder.model.system.v1_0_0.Entity;

import jakarta.validation.constraints.NotNull;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"edf_id", "url"})
@JsonTypeInfo(use = Id.NONE)
public class Endpoint extends Entity {
	
	@JsonIgnore
	public static final String ID = "urn:com.ibm.pathfinder.model.dataobs:endpoint:1.1.0";

	@JsonIgnore @Serial private static final long serialVersionUID = -1889256505769660624L;

	@JsonProperty("url")
	@JsonPropertyDescription("The name of the Endpoint url.")
	@NotNull
	private String url;
	
	public Endpoint() {
		super();
		this.setJsonSchemaRef(ID);
	}
	
	@JsonProperty("url")
	public String getUrl() {
		return url;
	}

	@JsonProperty("url")
	public void setUrl(String url) {
		this.url = url;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		if (!super.equals(o)) {
			return false;
		}

		Endpoint that = (Endpoint) o;
		return Objects.equals(url, that.url);
	}

	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + (url != null ? url.hashCode() : 0);
		return result;
	}
}
