package com.stachura.praca_inz.backend.service.impl;

import com.stachura.praca_inz.backend.exception.repository.DatabaseErrorException;
import com.stachura.praca_inz.backend.exception.repository.EntityException;
import com.stachura.praca_inz.backend.exception.service.ServiceException;
import com.stachura.praca_inz.backend.model.Report;
import com.stachura.praca_inz.backend.repository.interfaces.ReportRepository;
import com.stachura.praca_inz.backend.service.ReportService;
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

    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('REPORT_READ')")
    public Report getReportById(Long id) {
        Report report = reportRepository.find(id);
        if (report.isDeleted()) {
            return null;
        }
        return report;
    }

    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('REPORT_LIST_READ')")
    public List<ReportListElementDto> getAllReports() {
        List<Report> reports = reportRepository.findAll();
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
        List<Report> reports = reportRepository.findAll().stream().filter(x -> x.getSender().getUsername().equals(username)).collect(Collectors.toList());
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
        List<Report> reports = reportRepository.findAll().stream().filter(x -> x.getReciever().getUsername().equals(username)).collect(Collectors.toList());
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
    public Long createNewReport(Report report) throws ServiceException {
        try {
            reportRepository.create(report);
            return report.getId();
        } catch (DatabaseErrorException e) {
            throw e;
        } catch (EntityException e) {
            throw ServiceException.createServiceException(ServiceException.ENTITY_VALIDATION, e);
        }
    }


    @Override
    @Transactional
    @PreAuthorize("hasAuthority('REPORT_UPDATE')")
    public void updateReport(Report report) throws ServiceException {
        reportRepository.update(report);
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('REPORT_DELETE')")
    public void deleteReportById(Long id) {
        reportRepository.find(id).setDeleted(true);
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('REPORT_DELETE')")
    public void deleteReport(Report report) {
        reportRepository.find(report.getId()).setDeleted(true);
    }



    @Override
    @Transactional
    @PreAuthorize("hasAuthority('REPORT_DELETE')")
    public void disableBySender(Long id) {
        reportRepository.find(id).setDisableSender(true);
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('REPORT_DELETE')")
    public void disableByReciever(Long id) {
        reportRepository.find(id).setDisableReciever(true);
    }
}
