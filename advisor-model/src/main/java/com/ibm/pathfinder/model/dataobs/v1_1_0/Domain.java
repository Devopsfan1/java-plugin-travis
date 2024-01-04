/*
 * IBM Confidential
 * PID 5900-B4I
 * © Copyright IBM Corp. 2023
 *
 */

package com.ibm.pathfinder.model.dataobs.v1_1_0;

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


/** The network location information. */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"domain"})
@JsonTypeInfo(use = Id.NONE)
public class Domain extends Entity {
  @JsonIgnore
  public static final String ID = "urn:com.ibm.pathfinder.model.dataobs:domain:1.1.0";

  @JsonIgnore @Serial private static final long serialVersionUID = -6576893025469875822L;
  

  @JsonProperty("dns")
  @JsonPropertyDescription("The domain distinguishes private network addresses.")
  @NotNull
  private String dns;

  /** No args constructor for use in serialization. */
  public Domain() {
    super();
    this.setJsonSchemaRef(ID);
  }

  /** The domain distinguishes private network addresses. */
  @JsonProperty("dns")
  public Optional<String> getDns() {
    return Optional.ofNullable(dns);
  }

  /** The domain distinguishes private network addresses. */
  @JsonProperty("dns")
  public void setDns(String dns) {
    this.dns = dns;
  }

  @Override
public String toString() {
	StringBuilder builder = new StringBuilder();
	builder.append("Domain [dns=");
	builder.append(dns);
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

    Domain that = (Domain) o;

    return Objects.equals(dns, that.dns);
  }

  @Override
  public int hashCode() {
    int result = super.hashCode();
    result = 31 * result + (dns != null ? dns.hashCode() : 0);
    return result;
  }
}
