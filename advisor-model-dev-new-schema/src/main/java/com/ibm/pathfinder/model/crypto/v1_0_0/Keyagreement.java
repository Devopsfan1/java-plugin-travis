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

/** A key agreement protocol. */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"edf_id", "algorithm"})
@JsonTypeInfo(use = Id.NONE)
public class Keyagreement extends Entity {

	@JsonIgnore
	public static final String ID = "urn:com.ibm.pathfinder.model.crypto:keyagreement:1.1.0";

	@JsonIgnore @Serial private static final long serialVersionUID = 3935594416215390763L;

	/** The name of the key exchange algorithm. */
	@JsonProperty("algorithm")
	@JsonPropertyDescription("The name of the key exchange algorithm.")
	@NotNull
	private String algorithm;

	/** No args constructor for use in serialization. */
	public Keyagreement() {
		super();
		this.setJsonSchemaRef(ID);
	}

	/** The name of the key exchange algorithm. */
	@JsonProperty("algorithm")
	public String getAlgorithm() {
		return algorithm;
	}

	/** The name of the key exchange algorithm. */
	@JsonProperty("algorithm")
	public void setAlgorithm(String algorithm) {
		this.algorithm = algorithm;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Keyagreement [algorithm=");
		builder.append(algorithm);
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

		Keyagreement that = (Keyagreement) o;

		return Objects.equals(algorithm, that.algorithm);
	}

	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + (algorithm != null ? algorithm.hashCode() : 0);
		return result;
	}
}
