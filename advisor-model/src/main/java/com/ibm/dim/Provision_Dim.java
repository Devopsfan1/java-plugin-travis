package com.ibm.dim;

import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Provision_Dim extends EntityNewSchema{
    private Integer provision_id;
    private LocalDateTime provision_snapshot_ts;
}
