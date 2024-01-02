/*
 * IBM Confidential
 * PID 5900-B4I
 * © Copyright IBM Corp. 2023
 *
 */

package com.ibm.pathfinder.model.crypto.v1_0_0;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.ibm.pathfinder.model.system.v1_0_0.Entity;

import jakarta.validation.constraints.NotNull;

import java.io.Serial;
import java.util.Objects;

/** A key generator. */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"edf_id", "name"})
@JsonTypeInfo(use = Id.NONE)
public class Keygenerator extends Entity {

	@JsonIgnore
	public static final String ID = "urn:com.ibm.pathfinder.model.crypto:keygenerator:1.1.0";

	@JsonIgnore @Serial private static final long serialVersionUID = 3512995362968440047L;

	/** The name of the key generator. */
	@JsonProperty("name")
	@JsonPropertyDescription("The name of the key generator.")
	@NotNull
	private String name;

	/** No args constructor for use in serialization. */
	public Keygenerator() {
		super();
		this.setJsonSchemaRef(ID);
	}

	/** The name of the key generator. */
	@JsonProperty("name")
	public String getName() {
		return name;
	}

	/** The name of the key generator. */
	@JsonProperty("name")
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Keygenerator [name=");
		builder.append(name);
		builder.append(", hashCode()=");
		builder.append(hashCode());
		builder.append(", getEdfId()=");
		builder.append(getEdfId());
		builder.append(", getJsonSchemaRef()=");
		builder.append(getJsonSchemaRef());
		builder.append("]");
		return builder.toString();
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

		Keygenerator that = (Keygenerator) o;

		return Objects.equals(name, that.name);
	}

	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + (name != null ? name.hashCode() : 0);
		return result;
	}
}
