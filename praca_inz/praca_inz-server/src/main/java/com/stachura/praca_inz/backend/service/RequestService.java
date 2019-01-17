package com.stachura.praca_inz.backend.service;

import com.stachura.praca_inz.backend.exception.base.AppBaseException;
import com.stachura.praca_inz.backend.model.Request;
import com.stachura.praca_inz.backend.web.dto.device.DeviceListElementDto;
import com.stachura.praca_inz.backend.web.dto.request.ChangeStatusDto;
import com.stachura.praca_inz.backend.web.dto.request.DeviceRequestAddDto;
import com.stachura.praca_inz.backend.web.dto.request.RequestListElementDto;
import com.stachura.praca_inz.backend.web.dto.request.TransferRequestAddDto;

import java.util.List;

public interface RequestService {

    Request getRequestById(Long id) throws AppBaseException;

    List<RequestListElementDto> getAllRequests(String type,String username) throws AppBaseException;

    List<RequestListElementDto> getAllRequestsForUser(String username);

    List<RequestListElementDto> getAllRequestForManager(String username) throws AppBaseException;

    List<RequestListElementDto> getAllRequestFromeOtherUsers(String username) throws AppBaseException;

    List<RequestListElementDto> getAllRequestForWarehouseman(String username) throws AppBaseException;

    List<RequestListElementDto> getAllRequestFromOtherWarehouses(String username) throws AppBaseException;

    List<RequestListElementDto> getAllRequestsForOffice(String type,Long id);

    void createNewTransferRequest(TransferRequestAddDto transferRequestAddDto,String username) throws AppBaseException;

    void createNewDeviceRequest(DeviceRequestAddDto deviceRequestAddDto, String username) throws AppBaseException;

    void updateRequest(Request report) throws AppBaseException;

    void deleteRequestById(Long id) throws AppBaseException;

    void realizeRequest(ChangeStatusDto changeStatusDto) throws AppBaseException;

    List<DeviceListElementDto> getAllRequestDevices(Long id) throws AppBaseException;

    void addDevicesToRequest(List<Long> devices,Long requestId)throws AppBaseException;
}
