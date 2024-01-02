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

/** A description of a public key encryption. */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"edf_id", "algorithm", "key_length"})
@JsonTypeInfo(use = Id.NONE)
public class Pkencryption extends Entity {

	@JsonIgnore
	public static final String ID = "urn:com.ibm.pathfinder.model.crypto:pkencryption:1.1.0";

	@JsonIgnore @Serial private static final long serialVersionUID = 2533213184680901527L;

	/** The name of the public key encryption algorithm. */
	@JsonProperty("algorithm")
	@JsonPropertyDescription("The name of the public key encryption algorithm.")
	@NotNull
	private String algorithm;

	/** The length of the encryption key. */
	@JsonProperty("key_length")
	@JsonPropertyDescription("The length of the encryption key.")
	@NotNull
	private Long keyLength;

	/** No args constructor for use in serialization. */
	public Pkencryption() {
		super();
		this.setJsonSchemaRef(ID);
	}

	/** The name of the public key encryption algorithm. */
	@JsonProperty("algorithm")
	public String getAlgorithm() {
		return algorithm;
	}

	/** The name of the public key encryption algorithm. */
	@JsonProperty("algorithm")
	public void setAlgorithm(String algorithm) {
		this.algorithm = algorithm;
	}

	/** The length of the encryption key. */
	@JsonProperty("key_length")
	public Long getKeyLength() {
		return keyLength;
	}

	/** The length of the encryption key. */
	@JsonProperty("key_length")
	public void setKeyLength(Long keyLength) {
		this.keyLength = keyLength;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Pkencryption [algorithm=");
		builder.append(algorithm);
		builder.append(", keyLength=");
		builder.append(keyLength);
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

		Pkencryption that = (Pkencryption) o;

		if (!Objects.equals(algorithm, that.algorithm)) {
			return false;
		}
		return Objects.equals(keyLength, that.keyLength);
	}

	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + (algorithm != null ? algorithm.hashCode() : 0);
		result = 31 * result + (keyLength != null ? keyLength.hashCode() : 0);
		return result;
	}
}
