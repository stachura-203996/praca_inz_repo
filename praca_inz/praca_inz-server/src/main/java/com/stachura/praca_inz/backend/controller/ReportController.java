package com.stachura.praca_inz.backend.controller;

import com.stachura.praca_inz.backend.exception.EntityNotInDatabaseException;
import com.stachura.praca_inz.backend.exception.base.AppBaseException;
import com.stachura.praca_inz.backend.model.Report;
import com.stachura.praca_inz.backend.repository.UserRepository;
import com.stachura.praca_inz.backend.service.NotificationService;
import com.stachura.praca_inz.backend.service.ReportService;
import com.stachura.praca_inz.backend.web.dto.converter.ReportConverter;
import com.stachura.praca_inz.backend.web.dto.report.ReportAddDto;
import com.stachura.praca_inz.backend.web.dto.report.ReportListElementDto;
import com.stachura.praca_inz.backend.web.dto.report.ReportViewDto;
import com.stachura.praca_inz.backend.web.utils.NotificationMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/secured/report")
@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor = AppBaseException.class)
public class ReportController {

    @Autowired
    private ReportService reportService;

    @Autowired
    private NotificationService notificationService;


    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    List<ReportListElementDto> getAllReports() throws AppBaseException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return reportService.getAllReports(auth.getName());
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    List<ReportListElementDto> getAllReportsForUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return reportService.getAllReportsForUser(auth.getName());
    }

    @RequestMapping(value = "/others", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    List<ReportListElementDto> getAllReportsFromOthers() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return reportService.getAllReportsFromOthers(auth.getName());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    ReportViewDto getReportsById(@PathVariable Long id) throws AppBaseException {
        return ReportConverter.toReportViewElement(reportService.getReportById(id));
    }

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<?> create(@RequestBody ReportAddDto reportAddDto) throws AppBaseException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            Report report = reportService.createNewReport(reportAddDto,auth.getName());
            notificationService.createNewNotification(NotificationMessages.getReportSentNotifiaction(report));
            notificationService.createNewNotification(NotificationMessages.getReportReceivedNotifiaction(report));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public void update(@RequestBody Report report) throws AppBaseException {
            reportService.updateReport(report);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public void delete(@PathVariable Long id) throws AppBaseException {
        reportService.deleteReportById(id);
    }

    @RequestMapping(value = "/sender/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public void diabledBySender(@PathVariable Long id) throws AppBaseException {
        reportService.disableBySender(id);
    }

    @RequestMapping(value = "/reciever/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public void disableByReciever(@PathVariable Long id) throws AppBaseException {
        reportService.disableByReciever(id);
    }

}
