package com.ibm.dim;

import lombok.*;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class CryptoFunc_Dim extends EntityNewSchema{
    private UUID cryptofunc_uuid;
    private String cryptofunc_signature;
    private String cryptofunc_lib;
}
