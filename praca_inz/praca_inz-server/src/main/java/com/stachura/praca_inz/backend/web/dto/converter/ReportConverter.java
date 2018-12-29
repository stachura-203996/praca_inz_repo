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
                .sender(report.getSender().getUsername())
                .reciver(report.getReciever().getUsername())
                .description(report.getDescription())
                .reportDate(report.getCalendarTimestamp().getTime().toString())
                .build();
    }
}
