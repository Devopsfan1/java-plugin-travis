/*
 * IBM Confidential
 * PID 5900-B4I
 * © Copyright IBM Corp. 2023
 *
 */
package com.ibm.advisor.plugin.error;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;


public class GenericErrorPojo {
	  private int statusCode;
	  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
	  private LocalDateTime timestamp;
	  private String message;
	  private String description;
	  
	  public GenericErrorPojo() {
		  this.timestamp = LocalDateTime.now();
	  }
	  
	  public GenericErrorPojo(String message, int statusCode) {
		  this.timestamp = LocalDateTime.now();
		  this.message=message;
		  this.statusCode=statusCode;
	  }
	  public GenericErrorPojo(String message, String description, int statusCode) {
		  this.timestamp = LocalDateTime.now();
		  this.message=message;
		  this.description=description;
		  this.statusCode=statusCode;
	  }

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	  
}
