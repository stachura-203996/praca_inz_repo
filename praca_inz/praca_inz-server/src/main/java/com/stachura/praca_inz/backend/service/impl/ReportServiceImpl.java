package com.stachura.praca_inz.backend.service.impl;

import com.google.common.collect.Lists;
import com.stachura.praca_inz.backend.Constants;
import com.stachura.praca_inz.backend.exception.EntityNotInDatabaseException;
import com.stachura.praca_inz.backend.model.Report;
import com.stachura.praca_inz.backend.model.security.User;
import com.stachura.praca_inz.backend.repository.ReportRepository;
import com.stachura.praca_inz.backend.repository.UserRepository;
import com.stachura.praca_inz.backend.service.ReportService;
import com.stachura.praca_inz.backend.web.dto.converter.ReportConverter;
import com.stachura.praca_inz.backend.web.dto.report.ReportAddDto;
import com.stachura.praca_inz.backend.web.dto.report.ReportListElementDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
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
    @Transactional(readOnly = true,propagation = Propagation.MANDATORY)
    @PreAuthorize("hasAuthority('REPORT_READ')")
    public Report getReportById(Long id) throws EntityNotInDatabaseException {
        Report report = null;
            report = reportRepository.findById(id).orElseThrow(() -> new EntityNotInDatabaseException(EntityNotInDatabaseException.NO_OBJECT));
        if (report.isDeleted()) {
            return null;
        }
        return report;
    }

    @Override
    @Transactional(readOnly = true,propagation = Propagation.MANDATORY)
    @PreAuthorize("hasAuthority('REPORT_LIST_READ')")
    public List<ReportListElementDto> getAllReports(String username) throws EntityNotInDatabaseException {
        List<Report> reports;
        User user=userRepository.findByUsername(username).orElseThrow(()->new EntityNotInDatabaseException(EntityNotInDatabaseException.NO_OBJECT));
        if(user.getUserRoles().stream().anyMatch(x->x.getName().equals(Constants.ADMIN_ROLE))) {
            reports = Lists.newArrayList(reportRepository.findAll());
        } else{
            reports = Lists.newArrayList(reportRepository.findAll()).stream().filter(x->x.getSender().getOffice().getDepartment().getCompany().getId().equals(user.getOffice().getDepartment().getCompany().getId())).collect(Collectors.toList());
        }
        List<ReportListElementDto> reportListElementDtos = new ArrayList<>();
        for (Report a : reports) {
            if (!a.isDeleted()) {
                reportListElementDtos.add(ReportConverter.toReportListElement(a));
            }
        }
        return reportListElementDtos;
    }

    @Override
    @Transactional(readOnly = true,propagation = Propagation.MANDATORY)
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
    @Transactional(readOnly = true,propagation = Propagation.MANDATORY)
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
    @Transactional(readOnly = true,propagation = Propagation.MANDATORY)
    @PreAuthorize("hasAuthority('REPORT_CREATE')")
    public Report createNewReport(ReportAddDto reportAddDto, String username) throws EntityNotInDatabaseException {
            User reciever=userRepository.findById(reportAddDto.getReciever()).orElseThrow(()->new EntityNotInDatabaseException(EntityNotInDatabaseException.NO_OBJECT));
            User sender=userRepository.findByUsername(username).orElseThrow(() -> new EntityNotInDatabaseException(EntityNotInDatabaseException.NO_OBJECT));
            Report report=ReportConverter.toReport(reportAddDto,reciever,sender);
            reportRepository.saveAndFlush(report);
            return report;
    }

    @Override
    @Transactional(readOnly = true,propagation = Propagation.MANDATORY)
    @PreAuthorize("hasAuthority('REPORT_UPDATE')")
    public void updateReport(Report report) {
        reportRepository.saveAndFlush(report);
    }

    @Override
    @Transactional(readOnly = true,propagation = Propagation.MANDATORY)
    @PreAuthorize("hasAuthority('REPORT_DELETE')")
    public void deleteReportById(Long id) throws EntityNotInDatabaseException {
        reportRepository.findById(id).orElseThrow(() -> new EntityNotInDatabaseException(EntityNotInDatabaseException.NO_OBJECT)).setDeleted(true);
    }

    @Override
    @Transactional(readOnly = true,propagation = Propagation.MANDATORY)
    @PreAuthorize("hasAuthority('REPORT_DELETE')")
    public void disableBySender(Long id) throws EntityNotInDatabaseException {
        reportRepository.findById(id).orElseThrow(() -> new EntityNotInDatabaseException(EntityNotInDatabaseException.NO_OBJECT)).setDisableSender(true);
    }

    @Override
    @Transactional(readOnly = true,propagation = Propagation.MANDATORY)
    @PreAuthorize("hasAuthority('REPORT_DELETE')")
    public void disableByReciever(Long id) throws EntityNotInDatabaseException {
        reportRepository.findById(id).orElseThrow(() -> new EntityNotInDatabaseException(EntityNotInDatabaseException.NO_OBJECT)).setDisableReciever(true);
    }
}
