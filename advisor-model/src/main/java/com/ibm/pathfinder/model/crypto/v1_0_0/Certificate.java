/*
 * IBM Confidential
 * PID 5900-B4I
 * © Copyright IBM Corp. 2023
 *
 */
package com.ibm.pathfinder.model.crypto.v1_0_0;

import com.fasterxml.jackson.annotation.JsonFormat;
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
import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;


/** A cryptographic certificate. */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
	"edf_id",
	"format",
	"version",
	"subject",
	"issuer",
	"algorithm",
	"signature_algo",
	"signature_algo_oid",
	"not_before",
	"not_after",
	"extensions"
})
@JsonTypeInfo(use = Id.NONE)
public class Certificate extends Entity {

	@JsonIgnore
	public static final String ID = "urn:com.ibm.pathfinder.model.crypto:certificate:1.1.0";

	@JsonIgnore @Serial private static final long serialVersionUID = 6667607931883137281L;

	/** The format (representation?) of the certificate. */
	@JsonProperty("format")
	@JsonPropertyDescription("The format (representation?) of the certificate.")
	private String format;
	/** The version of the certificate. */
	@JsonProperty("version")
	@JsonPropertyDescription("The version of the certificate.")
	private String version;
	/** The subject for which the certificate was issued. */
	@JsonProperty("subject")
	@JsonPropertyDescription("The subject for which the certificate was issued.")
	private String subject;
	/** The issuer of the certificate. */
	@JsonProperty("issuer")
	@JsonPropertyDescription("The issuer of the certificate.")
	@NotNull
	private String issuer;
	/** The algorithm used to verify the certificate. */
	@JsonProperty("algorithm")
	@JsonPropertyDescription("The algorithm used to verify the certificate.")
	private String algorithm;
	/** The the algorithm that was used to sign the certificate. */
	@JsonProperty("signature_algo")
	@JsonPropertyDescription("The the algorithm that was used to sign the certificate.")
	@NotNull
	private String signatureAlgo;
	/** The object identifier of the signature algorithm. */
	@JsonProperty("signature_algo_oid")
	@JsonPropertyDescription("The object identifier of the signature algorithm.")
	private String signatureAlgoOid;
	/** The start of the validity period. */
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@JsonProperty("not_before")
	@JsonPropertyDescription("The start of the validity period.")
	private LocalDate notBefore;
	/** The end of the validity period. */
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@JsonProperty("not_after")
	@JsonPropertyDescription("The end of the validity period.")
	private LocalDate notAfter;
	/** Extensions. */
	@JsonProperty("extensions")
	@JsonPropertyDescription("Extensions.")
	private String extensions;

	/** No args constructor for use in serialization. */
	public Certificate() {
		super();
		this.setJsonSchemaRef(ID);
	}

	/** The format (representation?) of the certificate. */
	@JsonProperty("format")
	public Optional<String> getFormat() {
		return Optional.ofNullable(format);
	}

	/** The format (representation?) of the certificate. */
	@JsonProperty("format")
	public void setFormat(String format) {
		this.format = format;
	}

	/** The version of the certificate. */
	@JsonProperty("version")
	public Optional<String> getVersion() {
		return Optional.ofNullable(version);
	}

	/** The version of the certificate. */
	@JsonProperty("version")
	public void setVersion(String version) {
		this.version = version;
	}

	/** The subject for which the certificate was issued. */
	@JsonProperty("subject")
	public Optional<String> getSubject() {
		return Optional.ofNullable(subject);
	}

	/** The subject for which the certificate was issued. */
	@JsonProperty("subject")
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/** The issuer of the certificate. */
	@JsonProperty("issuer")
	public String getIssuer() {
		return issuer;
	}

	/** The issuer of the certificate. */
	@JsonProperty("issuer")
	public void setIssuer(String issuer) {
		this.issuer = issuer;
	}

	/** The algorithm used to verify the certificate. */
	@JsonProperty("algorithm")
	public Optional<String> getAlgorithm() {
		return Optional.ofNullable(algorithm);
	}

	/** The algorithm used to verify the certificate. */
	@JsonProperty("algorithm")
	public void setAlgorithm(String algorithm) {
		this.algorithm = algorithm;
	}

	/** The the algorithm that was used to sign the certificate. */
	@JsonProperty("signature_algo")
	public String getSignatureAlgo() {
		return signatureAlgo;
	}

	/** The the algorithm that was used to sign the certificate. */
	@JsonProperty("signature_algo")
	public void setSignatureAlgo(String signatureAlgo) {
		this.signatureAlgo = signatureAlgo;
	}

	/** The object identifier of the signature algorithm. */
	@JsonProperty("signature_algo_oid")
	public Optional<String> getSignatureAlgoOid() {
		return Optional.ofNullable(signatureAlgoOid);
	}

	/** The object identifier of the signature algorithm. */
	@JsonProperty("signature_algo_oid")
	public void setSignatureAlgoOid(String signatureAlgoOid) {
		this.signatureAlgoOid = signatureAlgoOid;
	}
	/*
	 * IBM Confidential
	 * PID 5900-B4I
	 * © Copyright IBM Corp. 2023
	 *
	 */
	/** The start of the validity period. */
	@JsonProperty("not_before")
	public Optional<LocalDate> getNotBefore() {
		return Optional.ofNullable(notBefore);
	}

	/** The start of the validity period. */
	@JsonProperty("not_before")
	public void setNotBefore(LocalDate notBefore) {
		this.notBefore = notBefore;
	}

	/** The end of the validity period. */
	@JsonProperty("not_after")
	public Optional<LocalDate> getNotAfter() {
		return Optional.ofNullable(notAfter);
	}

	/** The end of the validity period. */
	@JsonProperty("not_after")
	public void setNotAfter(LocalDate notAfter) {
		this.notAfter = notAfter;
	}

	/** Extensions. */
	@JsonProperty("extensions")
	public Optional<String> getExtensions() {
		return Optional.ofNullable(extensions);
	}

	/** Extensions. */
	@JsonProperty("extensions")
	public void setExtensions(String extensions) {
		this.extensions = extensions;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Certificate [format=");
		builder.append(format);
		builder.append(", version=");
		builder.append(version);
		builder.append(", subject=");
		builder.append(subject);
		builder.append(", issuer=");
		builder.append(issuer);
		builder.append(", algorithm=");
		builder.append(algorithm);
		builder.append(", signatureAlgo=");
		builder.append(signatureAlgo);
		builder.append(", signatureAlgoOid=");
		builder.append(signatureAlgoOid);
		builder.append(", notBefore=");
		builder.append(notBefore);
		builder.append(", notAfter=");
		builder.append(notAfter);
		builder.append(", extensions=");
		builder.append(extensions);
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

		Certificate that = (Certificate) o;

		if (!Objects.equals(format, that.format)) {
			return false;
		}
		if (!Objects.equals(version, that.version)) {
			return false;
		}
		if (!Objects.equals(subject, that.subject)) {
			return false;
		}
		if (!Objects.equals(issuer, that.issuer)) {
			return false;
		}
		if (!Objects.equals(algorithm, that.algorithm)) {
			return false;
		}
		if (!Objects.equals(signatureAlgo, that.signatureAlgo)) {
			return false;
		}
		if (!Objects.equals(signatureAlgoOid, that.signatureAlgoOid)) {
			return false;
		}
		if (!Objects.equals(notBefore, that.notBefore)) {
			return false;
		}
		if (!Objects.equals(notAfter, that.notAfter)) {
			return false;
		}
		return Objects.equals(extensions, that.extensions);
	}

	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + (format != null ? format.hashCode() : 0);
		result = 31 * result + (version != null ? version.hashCode() : 0);
		result = 31 * result + (subject != null ? subject.hashCode() : 0);
		result = 31 * result + (issuer != null ? issuer.hashCode() : 0);
		result = 31 * result + (algorithm != null ? algorithm.hashCode() : 0);
		result = 31 * result + (signatureAlgo != null ? signatureAlgo.hashCode() : 0);
		result = 31 * result + (signatureAlgoOid != null ? signatureAlgoOid.hashCode() : 0);
		result = 31 * result + (notBefore != null ? notBefore.hashCode() : 0);
		result = 31 * result + (notAfter != null ? notAfter.hashCode() : 0);
		result = 31 * result + (extensions != null ? extensions.hashCode() : 0);
		return result;
	}
}
