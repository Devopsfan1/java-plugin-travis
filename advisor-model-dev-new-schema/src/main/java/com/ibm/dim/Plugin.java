package com.ibm.dim;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Plugin extends EntityNewSchema{
    private Integer plugin_id;
    private String plugin_name;
    private String plugin_type;
    private String plugin_desc;
}
