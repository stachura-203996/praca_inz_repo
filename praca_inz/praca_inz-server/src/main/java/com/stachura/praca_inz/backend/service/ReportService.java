package com.stachura.praca_inz.backend.service;

import com.stachura.praca_inz.backend.exception.service.ServiceException;
import com.stachura.praca_inz.backend.model.Report;
import com.stachura.praca_inz.backend.web.dto.report.ReportAddDto;
import com.stachura.praca_inz.backend.web.dto.report.ReportListElementDto;

import java.util.List;

public interface ReportService {
    Report getReportById(Long id);

    List<ReportListElementDto> getAllReports();

    Report createNewReport(ReportAddDto reportAddDto, String username) throws ServiceException;

    void updateReport(Report report) throws ServiceException;

    void deleteReportById(Long id) throws ServiceException;

    List<ReportListElementDto> getAllReportsFromOthers(String username);

    List<ReportListElementDto> getAllReportsForUser(String username);

    void disableBySender(Long id) throws ServiceException;

    void disableByReciever(Long id) throws ServiceException;
}
