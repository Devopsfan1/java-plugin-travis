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

/** A cryptographic protocol. */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"edf_id", "name", "type", "purpose", "version"})
@JsonTypeInfo(use = Id.NONE)
public class Cryptographicprotocol extends Entity {

	@JsonIgnore
	public static final String ID =
	"urn:com.ibm.pathfinder.model.crypto:cryptographicprotocol:1.1.0";

	@JsonIgnore @Serial private static final long serialVersionUID = -4773081765519933889L;

	/** The name of the protocol. */
	@JsonProperty("name")
	@JsonPropertyDescription("The name of the protocol.")
	@NotNull
	private String name;

	@JsonProperty("type")
	@JsonPropertyDescription("The type of the protocol, e.g. IPSEC.")
	@NotNull
	private String type;

	@JsonProperty("purpose")
	@JsonPropertyDescription("The purpose of the protocol.")
	private String purpose;

	@JsonProperty("version")
	@JsonPropertyDescription("The protocol version such as '1.3'.")
	private String version;

	/** No args constructor for use in serialization. */
	public Cryptographicprotocol() {
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

	/** The type of the protocol, e.g. IPSEC. */
	@JsonProperty("type")
	public String getType() {
		return type;
	}

	/** The type of the protocol, e.g. IPSEC. */
	@JsonProperty("type")
	public void setType(String type) {
		this.type = type;
	}

	@JsonProperty("purpose")
	public Optional<String> getPurpose() {
		return Optional.ofNullable(purpose);
	}

	@JsonProperty("purpose")
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	@JsonProperty("version")
	public Optional<String> getVersion() {
		return Optional.ofNullable(version);
	}

	@JsonProperty("version")
	public void setVersion(String version) {
		this.version = version;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Cryptographicprotocol [name=");
		builder.append(name);
		builder.append(", type=");
		builder.append(type);
		builder.append(", purpose=");
		builder.append(purpose);
		builder.append(", version=");
		builder.append(version);
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

		Cryptographicprotocol that = (Cryptographicprotocol) o;

		if (!Objects.equals(name, that.name)) {
			return false;
		}
		if (!Objects.equals(type, that.type)) {
			return false;
		}
		if (!Objects.equals(purpose, that.purpose)) {
			return false;
		}
		return Objects.equals(version, that.version);
	}

	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + (name != null ? name.hashCode() : 0);
		result = 31 * result + (type != null ? type.hashCode() : 0);
		result = 31 * result + (purpose != null ? purpose.hashCode() : 0);
		result = 31 * result + (version != null ? version.hashCode() : 0);
		return result;
	}
}
