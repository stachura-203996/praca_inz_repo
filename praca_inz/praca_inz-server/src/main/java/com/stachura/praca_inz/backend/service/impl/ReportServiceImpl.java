package com.stachura.praca_inz.backend.service.impl;

import com.google.common.collect.Lists;
import com.stachura.praca_inz.backend.exception.repository.DatabaseErrorException;
import com.stachura.praca_inz.backend.exception.repository.EntityException;
import com.stachura.praca_inz.backend.exception.service.ServiceException;
import com.stachura.praca_inz.backend.model.Report;
import com.stachura.praca_inz.backend.model.security.User;
import com.stachura.praca_inz.backend.repository.ReportRepository;
import com.stachura.praca_inz.backend.repository.UserRepository;
import com.stachura.praca_inz.backend.service.ReportService;
import com.stachura.praca_inz.backend.web.dto.report.ReportAddDto;
import com.stachura.praca_inz.backend.web.dto.report.ReportListElementDto;
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
    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('REPORT_READ')")
    public Report getReportById(Long id) {
        Report report = null;
        try {
            report = reportRepository.findById(id).orElseThrow(() -> new ServiceException());
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        if (report.isDeleted()) {
            return null;
        }
        return report;
    }

    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('REPORT_LIST_READ')")
    public List<ReportListElementDto> getAllReports() {
        List<Report> reports = Lists.newArrayList(reportRepository.findAll());
        List<ReportListElementDto> reportListElementDtos = new ArrayList<>();
        for (Report a : reports) {
            if (!a.isDeleted()) {
                reportListElementDtos.add(ReportConverter.toReportListElement(a));
            }
        }
        return reportListElementDtos;
    }

    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('REPORT_LIST_READ')")
    public List<ReportListElementDto> getAllReportsForUser(String username) {
        List<Report> reports = Lists.newArrayList(reportRepository.findAll()).stream().filter(x -> x.getSender().getUsername().equals(username)).collect(Collectors.toList());
        List<ReportListElementDto> reportListElementDtos = new ArrayList<>();
        for (Report a : reports) {
            if (!a.isDeleted() && !a.isDisableSender()) {
                reportListElementDtos.add(ReportConverter.toReportListElement(a));
            }
        }
        return reportListElementDtos;
    }

    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('REPORT_LIST_READ')")
    public List<ReportListElementDto> getAllReportsFromOthers(String username) {
        List<Report> reports = Lists.newArrayList(reportRepository.findAll()).stream().filter(x -> x.getReciever().getUsername().equals(username)).collect(Collectors.toList());
        List<ReportListElementDto> reportListElementDtos = new ArrayList<>();
        for (Report a : reports) {
            if (!a.isDeleted() && !a.isDisableReciever()) {
                reportListElementDtos.add(ReportConverter.toReportListElement(a));
            }
        }
        return reportListElementDtos;
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('REPORT_CREATE')")
    public Report createNewReport(ReportAddDto reportAddDto, String username) throws ServiceException {
            User reciever=userRepository.findById(reportAddDto.getReciever()).orElseThrow(()->new ServiceException());
            User sender=userRepository.findByUsername(username).orElseThrow(() -> new ServiceException());
            Report report=ReportConverter.toReport(reportAddDto,reciever,sender);
            reportRepository.save(report);
            return report;
    }


    @Override
    @Transactional
    @PreAuthorize("hasAuthority('REPORT_UPDATE')")
    public void updateReport(Report report) throws ServiceException {
        reportRepository.save(report);
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('REPORT_DELETE')")
    public void deleteReportById(Long id) throws ServiceException {
        reportRepository.findById(id).orElseThrow(() -> new ServiceException()).setDeleted(true);
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('REPORT_DELETE')")
    public void disableBySender(Long id) throws ServiceException {
        reportRepository.findById(id).orElseThrow(() -> new ServiceException()).setDisableSender(true);
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('REPORT_DELETE')")
    public void disableByReciever(Long id) throws ServiceException {
        reportRepository.findById(id).orElseThrow(() -> new ServiceException()).setDisableReciever(true);
    }
}
