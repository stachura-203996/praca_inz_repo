package com.stachura.praca_inz.backend.service.impl;

import com.stachura.praca_inz.backend.exception.EntityException;
import com.stachura.praca_inz.backend.model.Report;
import com.stachura.praca_inz.backend.repository.interfaces.ReportRepository;
import com.stachura.praca_inz.backend.service.ReportService;
import com.stachura.praca_inz.backend.web.dto.ReportListElementDto;
import com.stachura.praca_inz.backend.web.dto.converter.ReportConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private ReportRepository reportRepository;

    @Override
    @Transactional(readOnly = true)
//    @PreAuthorize("hasAuthority('REPORT_READ')")
    public Report getReportById(Long id) {
        Report report= reportRepository.find(id);
        if(report.isDeleted()){
            return null;
        }
        return report;
    }

    @Override
    @Transactional(readOnly = true)
//    @PreAuthorize("hasAuthority('REPORT_LIST_READ')")
    public List<ReportListElementDto> getAllReports() {
        List<Report> reports = reportRepository.findAll();
        List<ReportListElementDto> reportListElementDtos = new ArrayList<>();
        for (Report a : reports) {
            if(!a.isDeleted()) {
                reportListElementDtos.add(ReportConverter.toReportListElement(a));
            }
        }
        return reportListElementDtos;
    }

    //TODO
    @Override
    @Transactional
//    @PreAuthorize("hasAuthority('REPORT_CREATE')")
    public void createNewReport(Report report) {
        try {
            reportRepository.create(report);

        } catch (EntityException e) {

        }

    }

    //TODO
    @Override
    @Transactional
//    @PreAuthorize("hasAuthority('REPORT_UPDATE')")
    public Report updateReport(Report report) {
        Report tmp = new Report();
        try {
            if (!reportRepository.find(report.getId()).isDeleted()) {
                tmp = reportRepository.update(report);
            }
        } catch (EntityException e) {

        }
        return tmp;
    }

    @Override
    @Transactional
//    @PreAuthorize("hasAuthority('REPORT_DELETE')")
    public void deleteReportById(Long id) {
        reportRepository.find(id).setDeleted(true);
    }

    @Override
    @Transactional
//    @PreAuthorize("hasAuthority('REPORT_DELETE')")
    public void deleteReport(Report report) {
        reportRepository.find(report.getId()).setDeleted(true);
    }

    @Override
    public List<ReportListElementDto> getAllReportsFromOthers(String username) {
        List<Report> reports = reportRepository.findAll().stream().filter(x->x.getReciever().getUsername().equals(username)).collect(Collectors.toList());
        List<ReportListElementDto> reportListElementDtos = new ArrayList<>();
        for (Report a : reports) {
            if(!a.isDeleted()) {
                reportListElementDtos.add(ReportConverter.toReportListElement(a));
            }
        }
        return reportListElementDtos;
    }

    @Override
    public List<ReportListElementDto> getAllReportsForUser(String username) {
        List<Report> reports = reportRepository.findAll().stream().filter(x->x.getSender().getUsername().equals(username)).collect(Collectors.toList());
        List<ReportListElementDto> reportListElementDtos = new ArrayList<>();
        for (Report a : reports) {
            if(!a.isDeleted()) {
                reportListElementDtos.add(ReportConverter.toReportListElement(a));
            }
        }
        return reportListElementDtos;
    }
}
