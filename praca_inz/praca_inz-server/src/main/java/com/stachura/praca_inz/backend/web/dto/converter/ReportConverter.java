package com.stachura.praca_inz.backend.web.dto.converter;

import com.stachura.praca_inz.backend.model.Report;
import com.stachura.praca_inz.backend.model.security.User;
import com.stachura.praca_inz.backend.repository.UserRepository;
import com.stachura.praca_inz.backend.web.dto.report.ReportAddDto;
import com.stachura.praca_inz.backend.web.dto.report.ReportListElementDto;
import com.stachura.praca_inz.backend.web.dto.report.ReportViewDto;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ReportConverter {

    public static ReportListElementDto toReportListElement(Report report){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return ReportListElementDto.builder()
                .id(report.getId())
                .title(report.getTitle())
                .sender(report.getSender().getUsername())
                .receiver(report.getReciever().getUsername())
                .reportDate(formatter.format(report.getCalendarTimestamp().getTime()))
                .senderName(report.getSender().getUserdata().getName())
                .senderSurname(report.getSender().getUserdata().getSurname())
                .recieverName(report.getReciever().getUserdata().getName())
                .recieverSurname(report.getReciever().getUserdata().getSurname())
                .build();
    }

    public static ReportViewDto toReportViewElement(Report report){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return ReportViewDto.builder()
                .id(report.getId())
                .title(report.getTitle())
                .sender(report.getSender().getUsername())
                .receiver(report.getReciever().getUsername())
                .description(report.getDescription())
                .reportDate(formatter.format(report.getCalendarTimestamp().getTime()))
                .senderName(report.getSender().getUserdata().getName())
                .senderSurname(report.getSender().getUserdata().getSurname())
                .recieverName(report.getReciever().getUserdata().getName())
                .recieverSurname(report.getReciever().getUserdata().getSurname())
                .build();
    }

    public static Report toReport(ReportAddDto reportAddDto, User reciever, User sender){
        Report report=new Report();
        report.setDisableReciever(false);
        report.setDisableSender(false);
        report.setCalendarTimestamp(Calendar.getInstance());
        report.setDeleted(false);
        report.setReciever(reciever);
        report.setDescription(reportAddDto.getDescription());
        report.setTitle(reportAddDto.getTitle());
        report.setSender(sender);
        return report;
    }

}
