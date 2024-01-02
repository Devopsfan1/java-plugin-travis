package com.ibm.dim;

import lombok.*;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Cert_Dim extends EntityNewSchema{
    private UUID cert_uuid;
    private String cert_format;
    private String cert_subject;
    private String cert_ver;
    private String cert_issuer;
    private String cert_algo;
    private String cert_sig_algo;
    private String cert_sig_algo_oid;
    private String cert_not_before;
    private String cert_not_after;
}
