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

/** A cryptographic key. */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"edf_id", "name", "id", "type", "length"})
@JsonTypeInfo(use = Id.NONE)
public class Key extends Entity {

	@JsonIgnore public static final String IDKEY = "urn:com.ibm.pathfinder.model.crypto:key:1.1.0";
	@JsonIgnore @Serial private static final long serialVersionUID = -8547693075443661799L;

	/** The name of the key. */
	@JsonProperty("name")
	@JsonPropertyDescription("The name of the key.")
	@NotNull
	private String name;

	@JsonProperty("id")
	@JsonPropertyDescription("The key's identifier")
	private String id;

	@JsonProperty("type")
	@JsonPropertyDescription("The type of the key, e.g. IPSEC")
	@NotNull
	private String type;

	@JsonProperty("length")
	@JsonPropertyDescription("The length of the key,")
	@NotNull
	private Long length;

	/** No args constructor for use in serialization. */
	public Key() {
		super();
		this.setJsonSchemaRef(IDKEY);
	}

	/** The name of the key. */
	@JsonProperty("name")
	public String getName() {
		return name;
	}

	/** The name of the key. */
	@JsonProperty("name")
	public void setName(String name) {
		this.name = name;
	}

	@JsonProperty("id")
	public Optional<String> getId() {
		return Optional.ofNullable(id);
	}

	@JsonProperty("id")
	public void setId(String id) {
		this.id = id;
	}

	@JsonProperty("type")
	public String getType() {
		return type;
	}

	@JsonProperty("type")
	public void setType(String type) {
		this.type = type;
	}

	@JsonProperty("length")
	public Long getLength() {
		return length;
	}

	@JsonProperty("length")
	public void setLength(Long length) {
		this.length = length;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Key [name=");
		builder.append(name);
		builder.append(", id=");
		builder.append(id);
		builder.append(", type=");
		builder.append(type);
		builder.append(", length=");
		builder.append(length);
		builder.append(", hashCode()=");
		builder.append(hashCode());
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

		Key key = (Key) o;

		if (!Objects.equals(name, key.name)) {
			return false;
		}
		if (!Objects.equals(id, key.id)) {
			return false;
		}
		if (!Objects.equals(type, key.type)) {
			return false;
		}
		return Objects.equals(length, key.length);
	}

	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + (name != null ? name.hashCode() : 0);
		result = 31 * result + (id != null ? id.hashCode() : 0);
		result = 31 * result + (type != null ? type.hashCode() : 0);
		result = 31 * result + (length != null ? length.hashCode() : 0);
		return result;
	}
}
