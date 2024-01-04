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
@JsonPropertyOrder({"edf_id", "region", "country_code", "city"})
@JsonTypeInfo(use = Id.NONE)
public class Geography extends Entity {
	
	@JsonIgnore
	public static final String ID = "urn:com.ibm.pathfinder.model.dataobs:geography:1.1.0";

	@JsonIgnore @Serial private static final long serialVersionUID = -1849299905769991624L;

	@JsonProperty("region")
	@JsonPropertyDescription("The region of the Geography.")
	@NotNull
	private String region;
	
	@JsonProperty("country_code")
	@JsonPropertyDescription("The country_code of the Geography.")
	@NotNull
	private String countryCode;
	
	@JsonProperty("city")
	@JsonPropertyDescription("The city of the Geography.")
	@NotNull
	private String city;
	
	public Geography() {
		super();
		this.setJsonSchemaRef(ID);
	}

	@JsonProperty("region")
	public String getRegion() {
		return region;
	}

	@JsonProperty("region")
	public void setRegion(String region) {
		this.region = region;
	}
	
	@JsonProperty("country_code")
	public String getCountryCode() {
		return countryCode;
	}

	@JsonProperty("country_code")
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	@JsonProperty("city")
	public String getCity() {
		return city;
	}
	
	@JsonProperty("city")
	public void setCity(String city) {
		this.city = city;
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

		Geography that = (Geography) o;

		if (!Objects.equals(city, that.city)) {
			return false;
		}
		if (!Objects.equals(countryCode, that.countryCode)) {
			return false;
		}
		return Objects.equals(region, that.region);
	}

	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + (region != null ? region.hashCode() : 0);
		result = 31 * result + (countryCode != null ? countryCode.hashCode() : 0);
		result = 31 * result + (city != null ? city.hashCode() : 0);
		return result;
	}
}
