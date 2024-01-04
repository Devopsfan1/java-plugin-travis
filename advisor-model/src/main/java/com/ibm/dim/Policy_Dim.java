package com.ibm.dim;

import lombok.*;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Policy_Dim extends EntityNewSchema{
    private UUID policy_uuid;
    private Integer policy_pkg;
    private Integer policy_rule_id;
    private Integer policy_severity;
    private String policy_query;
    private String policy_action;
    private String policy_desc;
    private String policy_alert_emails;
}
