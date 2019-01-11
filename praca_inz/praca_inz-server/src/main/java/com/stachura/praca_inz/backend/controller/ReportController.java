package com.stachura.praca_inz.backend.controller;

import com.stachura.praca_inz.backend.exception.service.ServiceException;
import com.stachura.praca_inz.backend.model.Notification;
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
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping("/secured/report")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private UserRepository userRepository;



    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    List<ReportListElementDto> getAllReports() {
        return reportService.getAllReports();
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
    ReportViewDto getReportsById(@PathVariable Long id) {
        return ReportConverter.toReportViewElement(reportService.getReportById(id));
    }

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<?> create(@RequestBody ReportAddDto reportAddDto) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        try {
            Report report = reportService.createNewReport(reportAddDto,auth.getName());
            notificationService.createNewNotification(NotificationMessages.getReportSentNotifiaction(report));
            notificationService.createNewNotification(NotificationMessages.getReportReceivedNotifiaction(report));
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public void update(@RequestBody Report report) {
        try {
            reportService.updateReport(report);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public void delete(@PathVariable Long id) throws ServiceException {
        reportService.deleteReportById(id);
    }

    @RequestMapping(value = "/sender/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public void diabledBySender(@PathVariable Long id) throws ServiceException {
        reportService.disableBySender(id);
    }

    @RequestMapping(value = "/reciever/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public void disableByReciever(@PathVariable Long id) throws ServiceException {
        reportService.disableByReciever(id);
    }




}
