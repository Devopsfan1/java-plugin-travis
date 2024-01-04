package com.ibm.dim;

import lombok.*;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Endpoint_dim extends EntityNewSchema{
    private UUID endpoint_uuid;
    private String endpoint_url;
}
