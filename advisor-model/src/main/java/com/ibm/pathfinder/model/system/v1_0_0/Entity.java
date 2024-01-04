/*
 * IBM Confidential
 * PID 5900-B4I
 * © Copyright IBM Corp. 2023
 *
 */

package com.ibm.pathfinder.model.system.v1_0_0;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.io.Serial;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;


public class Entity extends Payload {

	protected static final Object NOT_FOUND_VALUE = new Object();
	@JsonIgnore @Serial private static final long serialVersionUID = 3706455587443816573L;

	@JsonProperty("edf_id")
	@JsonPropertyDescription("Reference to the entity.")
	@NotNull
	private UUID edfId;

	@JsonProperty("verificationMessage")
	@JsonPropertyDescription("Reference to the entity verificationMessage")
	private Map<String, String> verificationMessage = new LinkedHashMap<>();

	@JsonIgnore @Valid private final Map<String, Object> additionalProperties = new LinkedHashMap<>();

	@JsonProperty("edf_id")
	public UUID getEdfId() {
		return edfId;
	}

	public void setEdfId(UUID edfId) {
		this.edfId = edfId;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

	@JsonProperty("verificationMessage")
	public Map<String, String> getVerificationMessage() {
		return verificationMessage;
	}

	public void setVerificationMessage(Map<String, String> verificationMessage) {
		this.verificationMessage = verificationMessage;
	}

	@Override
	protected Object declaredPropertyOrNotFound(String name, Object notFoundValue) {
		if ("edf_id".equals(name)) {
			return getEdfId();
		}
		return super.declaredPropertyOrNotFound(name, notFoundValue);
	}

	/**
	 * Method to retrieve generic attributes that aren't known at compile time. Returns the content of
	 * the property with the given name. Throws a class-cast exception if the type of the value
	 * doesn't match the (implicit) parameter type.
	 *
	 * @param name The name of the property.
	 * @param <T> The type of the value.
	 * @return The value of the property if it exists, null otherwise.
	 */
	@SuppressWarnings({"unchecked"})
	public <T> T get(String name) {
		Object value = declaredPropertyOrNotFound(name, NOT_FOUND_VALUE);
		if (NOT_FOUND_VALUE != value) {
			return ((T) value);
		} else {
			return ((T) getAdditionalProperties().get(name));
		}
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

		Entity entity = (Entity) o;

		if (!edfId.equals(entity.edfId)) {
			return false;
		}
		return additionalProperties.equals(entity.additionalProperties) && verificationMessage.equals(entity.verificationMessage);
	}

	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + edfId.hashCode();
		result = 31 * result + additionalProperties.hashCode();
		result = 31 * result + verificationMessage.hashCode();
		return result;
	}

	@JsonIgnore
	@Override
	public <T> T accept(PayloadVisitor<T> visitor) {
		return visitor.visit(this);
	}
}
