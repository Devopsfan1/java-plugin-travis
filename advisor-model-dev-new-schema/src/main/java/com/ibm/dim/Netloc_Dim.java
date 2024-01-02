package com.ibm.dim;

import lombok.*;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Netloc_Dim extends EntityNewSchema{
    private UUID netloc_uuid;
    private String netloc_host;
    private String netloc_port;
    private String netloc_dns;
    private String netloc_data_center;
    private String netloc_city;
    private String netloc_state_prov;
    private String netloc_country;
    private String netloc_region;
}
