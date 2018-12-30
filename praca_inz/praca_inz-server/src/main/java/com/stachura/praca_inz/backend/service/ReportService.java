package com.stachura.praca_inz.backend.service;

import com.stachura.praca_inz.backend.model.Report;
import com.stachura.praca_inz.backend.web.dto.ReportListElementDto;

import java.util.List;

public interface ReportService {
    Report getReportById(Long id);

    List<ReportListElementDto> getAllReports();

    void createNewReport(Report report);

    Report updateReport(Report report);

    void deleteReportById(Long id);

    void deleteReport(Report report);

    List<ReportListElementDto> getAllReportsFromOthers(String username);

    List<ReportListElementDto> getAllReportsForUser(String username);
}
