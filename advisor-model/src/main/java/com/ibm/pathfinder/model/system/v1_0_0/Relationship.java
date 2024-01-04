/*
 * IBM Confidential
 * PID 5900-B4I
 * © Copyright IBM Corp. 2023
 *
 */

package com.ibm.pathfinder.model.system.v1_0_0;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.io.Serial;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;


public class Relationship extends Payload {

  protected static final Object NOT_FOUND_VALUE = new Object();
  @JsonIgnore @Serial private static final long serialVersionUID = -5633868807672352398L;

  @JsonProperty("from_edf_id")
  @NotNull
  private UUID fromEdfId;

  @JsonProperty("to_edf_id")
  @NotNull
  private UUID toEdfId;

  @JsonIgnore @Valid private final Map<String, Object> additionalProperties = new LinkedHashMap<>();

  @JsonProperty("from_edf_id")
  public UUID getFromEdfId() {
    return fromEdfId;
  }

  public void setFromEdfId(UUID fromEdfId) {
    this.fromEdfId = fromEdfId;
  }

  @JsonProperty("to_edf_id")
  public UUID getToEdfId() {
    return toEdfId;
  }

  public void setToEdfId(UUID toEdfId) {
    this.toEdfId = toEdfId;
  }

  @JsonAnyGetter
  public Map<String, Object> getAdditionalProperties() {
    return this.additionalProperties;
  }

  @Override
  protected Object declaredPropertyOrNotFound(String name, Object notFoundValue) {
    return switch (name) {
      case "from_edf_id" -> getFromEdfId();
      case "to_edf_id" -> getToEdfId();
      default -> super.declaredPropertyOrNotFound(name, notFoundValue);
    };
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

    Relationship that = (Relationship) o;

    if (!fromEdfId.equals(that.fromEdfId)) {
      return false;
    }
    if (!toEdfId.equals(that.toEdfId)) {
      return false;
    }
    return additionalProperties.equals(that.additionalProperties);
  }

  @Override
  public int hashCode() {
    int result = super.hashCode();
    result = 31 * result + fromEdfId.hashCode();
    result = 31 * result + toEdfId.hashCode();
    result = 31 * result + additionalProperties.hashCode();
    return result;
  }

  @JsonIgnore
  @Override
  public <T> T accept(PayloadVisitor<T> visitor) {
    return visitor.visit(this);
  }
}
