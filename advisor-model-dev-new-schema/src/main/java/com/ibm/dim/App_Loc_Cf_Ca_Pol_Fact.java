package com.ibm.dim;

import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class App_Loc_Cf_Ca_Pol_Fact extends EntityNewSchema{
    private Integer cf_lib_policy_status;
    private Integer ca_algo_pol_status;
    private LocalDateTime policy_run_ts;
}
