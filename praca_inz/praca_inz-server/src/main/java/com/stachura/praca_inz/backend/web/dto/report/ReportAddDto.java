package com.stachura.praca_inz.backend.web.dto.report;

import lombok.*;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class ReportAddDto {
    String title;
    String description;
    Long reciever;
}
