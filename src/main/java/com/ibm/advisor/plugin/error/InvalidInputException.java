/*
 * IBM Confidential
 * PID 5900-B4I
 * © Copyright IBM Corp. 2023
 *
 */
package com.ibm.advisor.plugin.error;

public class InvalidInputException extends RuntimeException {
	  private static final long serialVersionUID = 1L;

	  public InvalidInputException(String msg) {
	    super(msg);
	  }
}
