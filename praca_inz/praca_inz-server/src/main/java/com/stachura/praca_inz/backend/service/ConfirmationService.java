package com.stachura.praca_inz.backend.service;

import com.stachura.praca_inz.backend.exception.EntityNotInDatabaseException;
import com.stachura.praca_inz.backend.exception.base.SystemBaseException;
import com.stachura.praca_inz.backend.model.Confirmation;
import com.stachura.praca_inz.backend.web.dto.confirmation.ConfirmationAddDto;
import com.stachura.praca_inz.backend.web.dto.confirmation.ConfirmationListElementDto;

import java.util.List;

public interface ConfirmationService {
    Confirmation getReportById(Long id) throws EntityNotInDatabaseException;

    List<ConfirmationListElementDto> getAllReports(String username) throws SystemBaseException;

    Confirmation createNewReport(ConfirmationAddDto confirmationAddDto, String username) throws SystemBaseException;

    void updateReport(Confirmation confirmation) throws SystemBaseException;

    void deleteReportById(Long id) throws SystemBaseException;

    List<ConfirmationListElementDto> getAllReportsFromOthers(String username);

    List<ConfirmationListElementDto> getAllReportsForUser(String username);

    void disableBySender(Long id) throws SystemBaseException;

    void disableByReciever(Long id) throws SystemBaseException;
}
