package com.ibm.dim;

import lombok.*;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Location_Dim extends EntityNewSchema{
    private UUID location_uuid;
    private String location_source_file;
    private Integer location_num_af;
    private String location_af_name;
    private Integer location_line_num;
    private Integer location_position;
}
