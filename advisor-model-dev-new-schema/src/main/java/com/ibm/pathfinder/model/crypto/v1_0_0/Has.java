/*
 * IBM Confidential
 * PID 5900-B4I
 * © Copyright IBM Corp. 2023
 *
 */

package com.ibm.pathfinder.model.crypto.v1_0_0;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.ibm.pathfinder.model.system.v1_0_0.Relationship;

@JsonTypeInfo(use = Id.NONE)
public class Has extends Relationship {
	@JsonIgnore private static final long serialVersionUID = 1L;
	@JsonIgnore public static final String ID = "urn:com.ibm.pathfinder.model.crypto:has:1.0.0";

	public Has() {
		this.setJsonSchemaRef(ID);
	}
}
