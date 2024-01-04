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

/** A grouping of cryptographic entities that are needed to secure a connection. */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"edf_id", "name", "security"})
@JsonTypeInfo(use = Id.NONE)
public class Ciphersuite extends Entity {

	@JsonIgnore
	public static final String ID = "urn:com.ibm.pathfinder.model.crypto:ciphersuite:1.1.0";

	@JsonIgnore @Serial private static final long serialVersionUID = -1779252605775661624L;

	/** The name of the protocol. */
	@JsonProperty("name")
	@JsonPropertyDescription("The name of the protocol.")
	@NotNull
	private String name;
	/** A description of the security level provided by this suite. */
	@JsonProperty("security")
	@JsonPropertyDescription("A description of the security level provided by this suite.")
	private String security;

	/** No args constructor for use in serialization. */
	public Ciphersuite() {
		super();
		this.setJsonSchemaRef(ID);
	}

	/** The name of the protocol. */
	@JsonProperty("name")
	public String getName() {
		return name;
	}

	/** The name of the protocol. */
	@JsonProperty("name")
	public void setName(String name) {
		this.name = name;
	}

	/** A description of the security level provided by this suite. */
	@JsonProperty("security")
	public Optional<String> getSecurity() {
		return Optional.ofNullable(security);
	}

	/** A description of the security level provided by this suite. */
	@JsonProperty("security")
	public void setSecurity(String security) {
		this.security = security;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Ciphersuite [name=");
		builder.append(name);
		builder.append(", security=");
		builder.append(security);
		builder.append(", getEdfId()=");
		builder.append(getEdfId());
		builder.append(", getClass()=");
		builder.append(getClass());
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

		Ciphersuite that = (Ciphersuite) o;

		if (!Objects.equals(name, that.name)) {
			return false;
		}
		return Objects.equals(security, that.security);
	}

	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + (name != null ? name.hashCode() : 0);
		result = 31 * result + (security != null ? security.hashCode() : 0);
		return result;
	}
}
