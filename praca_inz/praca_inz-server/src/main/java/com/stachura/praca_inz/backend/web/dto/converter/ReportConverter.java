package com.stachura.praca_inz.backend.web.dto.converter;

import com.stachura.praca_inz.backend.model.Report;
import com.stachura.praca_inz.backend.model.security.User;
import com.stachura.praca_inz.backend.repository.interfaces.UserRepository;
import com.stachura.praca_inz.backend.web.dto.report.ReportAddDto;
import com.stachura.praca_inz.backend.web.dto.report.ReportListElementDto;
import com.stachura.praca_inz.backend.web.dto.report.ReportViewDto;

import java.util.Calendar;

public class ReportConverter {

    public static ReportListElementDto toReportListElement(Report report){
        return ReportListElementDto.builder()
                .id(report.getId())
                .title(report.getTitle())
                .sender(report.getSender().getUsername())
                .receiver(report.getReciever().getUsername())
                .reportDate(report.getCalendarTimestamp().getTime().toString())
                .build();
    }

    public static ReportViewDto toReportViewElement(Report report){
        return ReportViewDto.builder()
                .id(report.getId())
                .title(report.getTitle())
                .sender(report.getSender().getUsername())
                .receiver(report.getReciever().getUsername())
                .description(report.getDescription())
                .reportDate(report.getCalendarTimestamp().getTime().toString())
                .build();
    }

    public static Report toReport(ReportAddDto reportAddDto, UserRepository userRepository, String sender){
        Report report=new Report();
        report.setDisableReciever(false);
        report.setDisableSender(false);
        report.setCalendarTimestamp(Calendar.getInstance());
        report.setDeleted(false);
        report.setReciever(userRepository.find(reportAddDto.getReciever()));
        report.setDescription(reportAddDto.getDescription());
        report.setTitle(reportAddDto.getTitle());
        report.setSender(userRepository.find(sender));
        return report;
    }

}
