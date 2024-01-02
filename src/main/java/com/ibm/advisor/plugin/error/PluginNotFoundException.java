/*
 * IBM Confidential
 * PID 5900-B4I
 * © Copyright IBM Corp. 2023
 *
 */
package com.ibm.advisor.plugin.error;

public class PluginNotFoundException extends RuntimeException {
	  private static final long serialVersionUID = 1L;

	  public PluginNotFoundException(String msg) {
	    super(msg);
	  }
}
