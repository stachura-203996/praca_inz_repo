package com.stachura.praca_inz.backend.web.dto.report;

import lombok.*;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class ReportViewDto {

    private Long id;
    private String title;
    private String sender;
    private String receiver;
    private String reportDate;
    private String description;
}
