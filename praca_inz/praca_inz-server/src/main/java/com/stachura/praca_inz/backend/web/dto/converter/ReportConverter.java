package com.stachura.praca_inz.backend.web.dto.converter;

import com.stachura.praca_inz.backend.model.Report;
import com.stachura.praca_inz.backend.model.Shipment;
import com.stachura.praca_inz.backend.web.dto.ReportListElementDto;
import com.stachura.praca_inz.backend.web.dto.ShipmentListElementDto;

public class ReportConverter {

    public static ReportListElementDto toReportListElement(Report report){
        return ReportListElementDto.builder()
                .id(report.getId())
                .title(report.getTitle())
                .username(report.getUser().getUsername())
                .build();
    }
}
