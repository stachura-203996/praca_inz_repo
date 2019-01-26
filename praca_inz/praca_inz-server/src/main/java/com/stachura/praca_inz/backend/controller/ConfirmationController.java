package com.stachura.praca_inz.backend.controller;

import com.stachura.praca_inz.backend.exception.base.AppBaseException;
import com.stachura.praca_inz.backend.model.Confirmation;
import com.stachura.praca_inz.backend.service.NotificationService;
import com.stachura.praca_inz.backend.service.ConfirmationService;
import com.stachura.praca_inz.backend.web.dto.converter.ConfirmationConverter;
import com.stachura.praca_inz.backend.web.dto.confirmation.ConfirmationAddDto;
import com.stachura.praca_inz.backend.web.dto.confirmation.ConfirmationListElementDto;
import com.stachura.praca_inz.backend.web.dto.confirmation.ConfirmationViewDto;
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
@RequestMapping("/secured/confirmation")
@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor = AppBaseException.class)
public class ConfirmationController {

    @Autowired
    private ConfirmationService confirmationService;

    @Autowired
    private NotificationService notificationService;


    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    List<ConfirmationListElementDto> getAllReports() throws AppBaseException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return confirmationService.getAllReports(auth.getName());
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    List<ConfirmationListElementDto> getAllReportsForUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return confirmationService.getAllReportsForUser(auth.getName());
    }

    @RequestMapping(value = "/others", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    List<ConfirmationListElementDto> getAllReportsFromOthers() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return confirmationService.getAllReportsFromOthers(auth.getName());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    ConfirmationViewDto getReportsById(@PathVariable Long id) throws AppBaseException {
        return ConfirmationConverter.toReportViewElement(confirmationService.getReportById(id));
    }

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<?> create(@RequestBody ConfirmationAddDto confirmationAddDto) throws AppBaseException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            Confirmation confirmation = confirmationService.createNewReport(confirmationAddDto,auth.getName());
            notificationService.createNewNotification(NotificationMessages.getReportSentNotifiaction(confirmation));
            notificationService.createNewNotification(NotificationMessages.getReportReceivedNotifiaction(confirmation));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public void update(@RequestBody Confirmation confirmation) throws AppBaseException {
            confirmationService.updateReport(confirmation);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public void delete(@PathVariable Long id) throws AppBaseException {
        confirmationService.deleteReportById(id);
    }

    @RequestMapping(value = "/sender/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public void diabledBySender(@PathVariable Long id) throws AppBaseException {
        confirmationService.disableBySender(id);
    }

    @RequestMapping(value = "/reciever/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public void disableByReciever(@PathVariable Long id) throws AppBaseException {
        confirmationService.disableByReciever(id);
    }

}
