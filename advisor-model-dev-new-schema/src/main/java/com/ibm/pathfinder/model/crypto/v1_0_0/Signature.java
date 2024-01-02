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
import java.util.Optional;

/** A signature algorithm. */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"edf_id", "algorithm", "algorithm_oid"})
@JsonTypeInfo(use = Id.NONE)
public class Signature extends Entity {

	@JsonIgnore
	public static final String ID = "urn:com.ibm.pathfinder.model.crypto:signature:1.1.0";

	@JsonIgnore @Serial private static final long serialVersionUID = 4489812915453665389L;

	/** The name of the signature algorithm. */
	@JsonProperty("algorithm")
	@JsonPropertyDescription("The name of the signature algorithm.")
	@NotNull
	private String algorithm;

	/** The object identifier of the algorithm, as defined by the IETF. */
	@JsonProperty("algorithm_oid")
	@JsonPropertyDescription("The object identifier of the algorithm, as defined by the IETF.")
	private String algorithmOid;

	/** No args constructor for use in serialization. */
	public Signature() {
		super();
		this.setJsonSchemaRef(ID);
	}

	/** The name of the signature algorithm. */
	@JsonProperty("algorithm")
	public String getAlgorithm() {
		return algorithm;
	}

	/** The name of the signature algorithm. */
	@JsonProperty("algorithm")
	public void setAlgorithm(String algorithm) {
		this.algorithm = algorithm;
	}

	/** The object identifier of the algorithm, as defined by the IETF. */
	@JsonProperty("algorithm_oid")
	public Optional<String> getAlgorithmOid() {
		return Optional.ofNullable(algorithmOid);
	}

	/** The object identifier of the algorithm, as defined by the IETF. */
	@JsonProperty("algorithm_oid")
	public void setAlgorithmOid(String algorithmOid) {
		this.algorithmOid = algorithmOid;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Signature [algorithm=");
		builder.append(algorithm);
		builder.append(", algorithmOid=");
		builder.append(algorithmOid);
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

		Signature signature = (Signature) o;

		if (!Objects.equals(algorithm, signature.algorithm)) {
			return false;
		}
		return Objects.equals(algorithmOid, signature.algorithmOid);
	}

	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + (algorithm != null ? algorithm.hashCode() : 0);
		result = 31 * result + (algorithmOid != null ? algorithmOid.hashCode() : 0);
		return result;
	}
}
