package com.stachura.praca_inz.backend.service;

import com.stachura.praca_inz.backend.exception.service.ServiceException;
import com.stachura.praca_inz.backend.model.Request;
import com.stachura.praca_inz.backend.web.dto.device.DeviceListElementDto;
import com.stachura.praca_inz.backend.web.dto.request.ChangeStatusDto;
import com.stachura.praca_inz.backend.web.dto.request.DeviceRequestAddDto;
import com.stachura.praca_inz.backend.web.dto.request.RequestListElementDto;
import com.stachura.praca_inz.backend.web.dto.request.TransferRequestAddDto;

import java.util.List;

public interface RequestService {

    Request getRequestById(Long id) throws ServiceException;

    List<RequestListElementDto> getAllRequests(String type);

    List<RequestListElementDto> getAllRequestsForUser(String username);

    List<RequestListElementDto> getAllRequestForManager(String username) throws ServiceException;

    List<RequestListElementDto> getAllRequestFromeOtherUsers(String username) throws ServiceException;

    List<RequestListElementDto> getAllRequestForWarehouseman(String username) throws ServiceException;

    List<RequestListElementDto> getAllRequestFromOtherWarehouses(String username) throws ServiceException;

    List<RequestListElementDto> getAllRequestsForOffice(String type,Long id);

    void createNewTransferRequest(TransferRequestAddDto transferRequestAddDto,String username) throws ServiceException;

    void createNewDeviceRequest(DeviceRequestAddDto deviceRequestAddDto, String username) throws ServiceException;

    void updateRequest(Request report) throws ServiceException;

    void deleteRequestById(Long id) throws ServiceException;

    void realizeRequest(ChangeStatusDto changeStatusDto) throws ServiceException;

    List<DeviceListElementDto> getAllRequestDevices(Long id) throws ServiceException;

    void addDevicesToRequest(List<Long> devices,Long requestId)throws ServiceException;
}
