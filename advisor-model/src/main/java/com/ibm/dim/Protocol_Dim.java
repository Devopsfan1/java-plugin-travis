package com.ibm.dim;

import lombok.*;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Protocol_Dim extends EntityNewSchema{
    private UUID protocol_uuid;
    private String protocol_name;
    private String protocol_type;
    private String protocol_ver;
}
