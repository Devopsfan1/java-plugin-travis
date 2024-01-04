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
@JsonPropertyOrder({"edf_id", "name", "env"})
@JsonTypeInfo(use = Id.NONE)
public class Application extends Entity {
	
	@JsonIgnore
	public static final String ID = "urn:com.ibm.pathfinder.model.dataobs:application:1.1.0";

	@JsonIgnore @Serial private static final long serialVersionUID = -1889252605765661624L;

	@JsonProperty("name")
	@JsonPropertyDescription("The name of the Application.")
	@NotNull
	private String name;
	
	@JsonProperty("env")
	@JsonPropertyDescription("The name of the env.")
	@NotNull
	private String env;
	
	public Application() {
		super();
		this.setJsonSchemaRef(ID);
	}
	
	@JsonProperty("name")
	public String getName() {
		return name;
	}

	@JsonProperty("name")
	public void setName(String name) {
		this.name = name;
	}
	
	@JsonProperty("env")
	public String getEnv() {
		return env;
	}

	@JsonProperty("env")
	public void setEnv(String env) {
		this.env = env;
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

		Application that = (Application) o;

		if (!Objects.equals(name, that.name)) {
			return false;
		}
		return Objects.equals(env, that.env);
	}

	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + (name != null ? name.hashCode() : 0);
		result = 31 * result + (env != null ? env.hashCode() : 0);
		return result;
	}
}
