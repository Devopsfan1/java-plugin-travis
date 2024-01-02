/*
 * IBM Confidential
 * PID 5900-B4I
 * © Copyright IBM Corp. 2023
 *
 */
package com.ibm.advisor.plugin.error;

public class DataNotFoundException extends RuntimeException {
	  private static final long serialVersionUID = 1L;

	  public DataNotFoundException(String msg) {
	    super(msg);
	  }
}
