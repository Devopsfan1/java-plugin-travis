package com.ibm.advisor.dto;

import java.util.List;

import org.json.simple.JSONObject;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder @ToString
public class JdbcEntityRequestDto {
	private List<JSONObject> dims;
	private List<JSONObject> facts;
}
