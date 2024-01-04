/*
 * IBM Confidential
 * PID 5900-B4I
 * © Copyright IBM Corp. 2023
 *
 */
package com.ibm.advisor.dto;

import com.ibm.dim.EntityNewSchema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EntityResponseDto {
    private long scan_id;
    private long plugin_id;
    private long user_id;
    private List<EntityNewSchema> dims;
    private List<EntityNewSchema> facts;


}
