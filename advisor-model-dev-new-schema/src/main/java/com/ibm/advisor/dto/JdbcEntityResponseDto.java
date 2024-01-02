package com.ibm.advisor.dto;

import java.util.List;

import org.json.simple.JSONObject;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class JdbcEntityResponseDto {
	private List<JSONObject> dims;
	private List<JSONObject> facts;
}
