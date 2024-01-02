/*
 * IBM Confidential
 * PID 5900-B4I
 * © Copyright IBM Corp. 2023
 *
 */
package com.ibm.advisor.dto;

import com.ibm.dim.EntityNewSchema;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class EntityRequestDto {
    private List<EntityNewSchema> dims;
    private List<EntityNewSchema> facts;
}
