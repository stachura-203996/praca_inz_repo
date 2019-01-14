package com.stachura.praca_inz.backend.service;

import com.stachura.praca_inz.backend.exception.EntityNotInDatabaseException;
import com.stachura.praca_inz.backend.exception.base.AppBaseException;
import com.stachura.praca_inz.backend.model.Report;
import com.stachura.praca_inz.backend.web.dto.report.ReportAddDto;
import com.stachura.praca_inz.backend.web.dto.report.ReportListElementDto;

import java.util.List;

public interface ReportService {
    Report getReportById(Long id) throws EntityNotInDatabaseException;

    List<ReportListElementDto> getAllReports(String username) throws AppBaseException;

    Report createNewReport(ReportAddDto reportAddDto, String username) throws AppBaseException;

    void updateReport(Report report) throws AppBaseException;

    void deleteReportById(Long id) throws AppBaseException;

    List<ReportListElementDto> getAllReportsFromOthers(String username);

    List<ReportListElementDto> getAllReportsForUser(String username);

    void disableBySender(Long id) throws AppBaseException;

    void disableByReciever(Long id) throws AppBaseException;
}
