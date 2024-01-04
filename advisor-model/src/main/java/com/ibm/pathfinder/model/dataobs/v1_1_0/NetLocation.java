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
@JsonPropertyOrder({"host", "port"})
@JsonTypeInfo(use = Id.NONE)
public class NetLocation extends Entity {
  @JsonIgnore
  public static final String ID = "urn:com.ibm.pathfinder.model.dataobs:net_location:1.1.0";

  @JsonIgnore @Serial private static final long serialVersionUID = 5393988994964522159L;

  @JsonProperty("host")
  @JsonPropertyDescription("host IP")
  @NotNull
  private String host;

  @JsonProperty("port")
  @JsonPropertyDescription("port number, optional")
  private Integer port;

  /** No args constructor for use in serialization. */
  public NetLocation() {
    super();
    this.setJsonSchemaRef(ID);
  }

  @JsonProperty("host")
  public String getHost() {
    return host;
  }

  @JsonProperty("host")
  public void setHost(String host) {
    this.host = host;
  }

  @JsonProperty("port")
  public Optional<Integer> getPort() {
    return Optional.ofNullable(port);
  }

  @JsonProperty("port")
  public void setPort(Integer port) {
    this.port = port;
  }

  @Override
public String toString() {
	StringBuilder builder = new StringBuilder();
	builder.append("NetLocation [host=");
	builder.append(host);
	builder.append(", port=");
	builder.append(port);
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

    NetLocation that = (NetLocation) o;

    if (!Objects.equals(host, that.host)) {
      return false;
    }
    return Objects.equals(port, that.port);
  }

  @Override
  public int hashCode() {
    int result = super.hashCode();
    result = 31 * result + (host != null ? host.hashCode() : 0);
    result = 31 * result + (port != null ? port.hashCode() : 0);
    return result;
  }
}
