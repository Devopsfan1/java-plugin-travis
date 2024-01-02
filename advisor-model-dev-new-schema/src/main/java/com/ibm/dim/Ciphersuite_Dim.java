package com.ibm.dim;

import lombok.*;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Ciphersuite_Dim extends EntityNewSchema{
    private UUID ciphersuite_uuid;
    private String ciphersuite_name;
    private String ciphersuite_kem;
    private String ciphersuite_signature;
    private String ciphersuite_enc_algo;
    private Integer ciphersuite_enc_algo_keylength;
    private String ciphersuite_enc_algo_mode;
    private String ciphersuite_hash;
    private String ciphersuite_pkenc;
    private String ciphersuite_mac;
    private String ciphersuite_strength;
}
