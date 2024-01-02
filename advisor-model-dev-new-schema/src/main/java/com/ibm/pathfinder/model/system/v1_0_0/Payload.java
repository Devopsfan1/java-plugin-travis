/*
 * IBM Confidential
 * PID 5900-B4I
 * © Copyright IBM Corp. 2023
 *
 */

package com.ibm.pathfinder.model.system.v1_0_0;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.io.Serial;
import java.io.Serializable;


// Can't instantiate payload. Tell Jackson to use deduction based polymorphism.
// Deduction will use the existence / absence of fields to decide whether a class fits (or not).
@JsonTypeInfo(use = Id.DEDUCTION)
@JsonSubTypes({@Type(Entity.class), @Type(Relationship.class)})
public abstract class Payload implements Serializable {

  @JsonIgnore @Serial private static final long serialVersionUID = 6518619293981658505L;

  @JsonProperty("json_schema_ref")
  @Pattern(
      regexp =
          "^urn:(?<groupid>[a-z._-]+):(?<schemaid>[a-z._-]+):"
              + "(?<version>[0-9]+\\.[0-9]+\\.[0-9]+)$")
  @NotNull
  private String jsonSchemaRef;

  @JsonProperty("json_schema_ref")
  public String getJsonSchemaRef() {
    return jsonSchemaRef;
  }

  public void setJsonSchemaRef(String jsonSchemaRef) {
    this.jsonSchemaRef = jsonSchemaRef;
  }

  protected Object declaredPropertyOrNotFound(String name, Object notFoundValue) {
    if ("json_schema_ref".equals(name)) {
      return this.getJsonSchemaRef();
    }
    return notFoundValue;
  }

  public abstract <T> T get(String name);

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Payload payload = (Payload) o;

    return jsonSchemaRef.equals(payload.jsonSchemaRef);
  }

  @Override
  public int hashCode() {
    return jsonSchemaRef.hashCode();
  }

  @JsonIgnore
  public abstract <T> T accept(PayloadVisitor<T> visitor);
}
