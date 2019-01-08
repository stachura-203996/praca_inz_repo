package com.stachura.praca_inz.backend.controller;

import com.stachura.praca_inz.backend.exception.service.ServiceException;
import com.stachura.praca_inz.backend.model.Notification;
import com.stachura.praca_inz.backend.model.Report;
import com.stachura.praca_inz.backend.service.NotificationService;
import com.stachura.praca_inz.backend.service.ReportService;
import com.stachura.praca_inz.backend.web.dto.report.ReportListElementDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${server.port}")
    private String port;

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
    Report getReportsById(@PathVariable Long id) {
        return reportService.getReportById(id);
    }

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<?> create(@RequestBody Report report) {
        Long id = null;
        try {
            id = reportService.createNewReport(report);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        Notification notification = new Notification();
        notification.setUrl("localhost:" + port + "/page/employees/reports/view" + id);
        notification.setUser(report.getSender());
        notification.setReaded(false);
        notification.setTitle("Report sended");
        notification.setCalendarTimestamp(Calendar.getInstance());
        notification.setDeleted(false);
        notification.setDescription("Your report was sent to:" + report.getReciever().getUsername() + "\n+ \n"
                + "Report title: " + report.getTitle() + "\n \n"
                + "Report description:" + report.getDescription() + "\n \n"
                + "localhost:" + port + "/page/employees/reports/view" + id);
        try {
            notificationService.createNewNotification(notification);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        Notification recieverNotification = notification.clone();
        recieverNotification.setDescription("You get report from:" + report.getSender().getUsername() + "\n+ \n"
                + "Report title: " + report.getTitle() + "\n \n"
                + "Report description:" + report.getDescription() + "\n \n"
                + "localhost:" + port + "/page/employees/reports/view" + id);
        recieverNotification.setUser(report.getReciever());
        HttpHeaders headers = new HttpHeaders();
        ControllerLinkBuilder linkBuilder = linkTo(methodOn(ReportController.class).getReportsById(id));
        headers.setLocation(linkBuilder.toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
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
    public void delete(@PathVariable Long id) {
        reportService.deleteReportById(id);
    }
}
