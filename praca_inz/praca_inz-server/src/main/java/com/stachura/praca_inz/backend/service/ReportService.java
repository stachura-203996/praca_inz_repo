package com.stachura.praca_inz.backend.service;

import com.stachura.praca_inz.backend.exception.service.ServiceException;
import com.stachura.praca_inz.backend.model.Report;
import com.stachura.praca_inz.backend.web.dto.ReportListElementDto;

import java.util.List;

public interface ReportService {
    Report getReportById(Long id);

    List<ReportListElementDto> getAllReports();

    Long createNewReport(Report report) throws ServiceException;

    void updateReport(Report report) throws ServiceException;

    void deleteReportById(Long id);

    void deleteReport(Report report);

    List<ReportListElementDto> getAllReportsFromOthers(String username);

    List<ReportListElementDto> getAllReportsForUser(String username);
}
