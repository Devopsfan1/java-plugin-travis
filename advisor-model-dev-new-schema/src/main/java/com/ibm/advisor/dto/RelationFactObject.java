/*
 * IBM Confidential
 * PID 5900-B4I 
 * Copyright (c) IBM Corp. 2023 
 */
package com.ibm.advisor.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder @ToString
public class RelationFactObject implements Cloneable{
	
	private long org_id;
	private long scan_id;
	private UUID Geography_uuid;
	private UUID NetLocation_uuid;
	private UUID Application_uuid;
	private UUID Endpoint_uuid;
	private UUID Domain_uuid;
	private UUID Certificate_uuid;
	private UUID Cryptographicprotocol_uuid;
	private UUID Ciphersuite_uuid;
	private UUID Hash_uuid;
	private UUID Pkencryption_uuid;
	
	@Override
	public Object clone() throws CloneNotSupportedException {

		return super.clone();
	}

}
