package com.stachura.praca_inz.backend.service;

import com.stachura.praca_inz.backend.exception.base.SystemBaseException;
import com.stachura.praca_inz.backend.model.Request;
import com.stachura.praca_inz.backend.web.dto.device.DeviceListElementDto;
import com.stachura.praca_inz.backend.web.dto.request.ChangeStatusDto;
import com.stachura.praca_inz.backend.web.dto.request.DeviceRequestAddDto;
import com.stachura.praca_inz.backend.web.dto.request.RequestListElementDto;
import com.stachura.praca_inz.backend.web.dto.request.TransferRequestAddDto;

import java.util.List;

public interface RequestService {

    Request getRequestById(Long id) throws SystemBaseException;

    List<RequestListElementDto> getAllRequests(String type,String username) throws SystemBaseException;

    List<RequestListElementDto> getAllRequestsForUser(String username);

    List<RequestListElementDto> getAllRequestForManager(String username) throws SystemBaseException;

    List<RequestListElementDto> getAllRequestFromeOtherUsers(String username) throws SystemBaseException;

    List<RequestListElementDto> getAllRequestForWarehouseman(String username) throws SystemBaseException;

    List<RequestListElementDto> getAllRequestsForOffice(String type,Long id);

    void createNewTransferRequest(TransferRequestAddDto transferRequestAddDto,String username) throws SystemBaseException;

    void createNewDeviceRequest(DeviceRequestAddDto deviceRequestAddDto, String username) throws SystemBaseException;

    void updateRequest(Request report) throws SystemBaseException;

    void deleteRequestById(Long id) throws SystemBaseException;

    void realizeRequest(ChangeStatusDto changeStatusDto) throws SystemBaseException;

    List<DeviceListElementDto> getAllRequestDevices(Long id) throws SystemBaseException;

    void addDevicesToRequest(List<Long> devices,Long requestId)throws SystemBaseException;
}
