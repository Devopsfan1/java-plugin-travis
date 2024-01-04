package com.ibm.dim;

import lombok.*;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class CryptoAlgo_Dim extends EntityNewSchema{
    private UUID cryptoalgo_uuid;
    private String cryptoalgo_algo_name;
}
