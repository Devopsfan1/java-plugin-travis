package com.ibm.dim;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class App_Dim extends EntityNewSchema{
    private UUID app_uuid;
    private Integer app_generic_id;
    private String app_name;
    private String app_ver;
    private LocalDateTime app_start_time;
    private LocalDateTime  app_end_time;
    private String app_client_specific;
    private String app_owner_email;
}
