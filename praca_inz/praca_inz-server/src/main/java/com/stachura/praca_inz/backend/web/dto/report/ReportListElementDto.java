package com.stachura.praca_inz.backend.web.dto.report;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.stachura.praca_inz.backend.model.security.User;
import lombok.*;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class ReportListElementDto {

    private Long id;
    private String title;
    private String sender;
    private String receiver;
    private String reportDate;
}
