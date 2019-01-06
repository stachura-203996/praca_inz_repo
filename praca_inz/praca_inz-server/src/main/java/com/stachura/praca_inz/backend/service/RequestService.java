package com.stachura.praca_inz.backend.service;

import com.stachura.praca_inz.backend.exception.service.ServiceException;
import com.stachura.praca_inz.backend.model.Request;
import com.stachura.praca_inz.backend.web.dto.request.RequestListElementDto;

import java.util.List;

public interface RequestService {

    Request getRequestById(Long id);

    List<RequestListElementDto> getAllRequests(String type);

    List<RequestListElementDto> getAllRequestsForUser(String username);

    List<RequestListElementDto> getAllRequestForManager(String username);

    List<RequestListElementDto> getAllRequestFromeOtherUsers(String username);

    List<RequestListElementDto> getAllRequestForWarehouseman(String username);

    List<RequestListElementDto> getAllRequestFromOtherWarehouses(String username);

    List<RequestListElementDto> getAllRequestsForOffice(String type,Long id);

    void createNewRequest(Request report) throws ServiceException;

    void updateRequest(Request report) throws ServiceException;

    void deleteRequestById(Long id);

    void deleteRequest(Request report);


}
