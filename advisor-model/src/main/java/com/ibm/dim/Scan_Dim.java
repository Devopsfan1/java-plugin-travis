package com.ibm.dim;

import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Scan_Dim extends EntityNewSchema{
    private Integer scan_id;
    private LocalDateTime scan_reg_ts;
    private Integer scan_plugin_id;
    private Integer scan_user_email;
    private String scan_type;
    private String scan_status;
    private String scan_desc;
    private LocalDateTime scan_data_ts;
}
