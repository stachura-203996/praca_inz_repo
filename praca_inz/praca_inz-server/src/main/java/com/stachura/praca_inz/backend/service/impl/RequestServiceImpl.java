package com.stachura.praca_inz.backend.service.impl;

import com.stachura.praca_inz.backend.exception.repository.DatabaseErrorException;
import com.stachura.praca_inz.backend.exception.repository.EntityException;
import com.stachura.praca_inz.backend.exception.service.ServiceException;
import com.stachura.praca_inz.backend.model.Device;
import com.stachura.praca_inz.backend.model.Notification;
import com.stachura.praca_inz.backend.model.Request;
import com.stachura.praca_inz.backend.model.Transfer;
import com.stachura.praca_inz.backend.model.enums.DeviceStatus;
import com.stachura.praca_inz.backend.model.enums.Status;
import com.stachura.praca_inz.backend.model.enums.WarehouseType;
import com.stachura.praca_inz.backend.model.security.User;
import com.stachura.praca_inz.backend.repository.interfaces.DeviceRepository;
import com.stachura.praca_inz.backend.repository.interfaces.RequestRepository;
import com.stachura.praca_inz.backend.repository.interfaces.TransferRepository;
import com.stachura.praca_inz.backend.repository.interfaces.UserRepository;
import com.stachura.praca_inz.backend.service.EmailService;
import com.stachura.praca_inz.backend.service.NotificationService;
import com.stachura.praca_inz.backend.service.RequestService;
import com.stachura.praca_inz.backend.web.dto.converter.DeviceConverter;
import com.stachura.praca_inz.backend.web.dto.device.DeviceListElementDto;
import com.stachura.praca_inz.backend.web.dto.request.ChangeStatusDto;
import com.stachura.praca_inz.backend.web.dto.request.RequestListElementDto;
import com.stachura.praca_inz.backend.web.dto.converter.RequestConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RequestServiceImpl implements RequestService {

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TransferRepository transferRepository;

    @Autowired
    private NotificationService notificationService;

    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('REQUEST_READ')")
    public Request getRequestById(Long id) {
        Request request = requestRepository.find(id);
        if (request.isDeleted()) {
            return null;
        }
        return request;
    }

    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('REQUEST_LIST_READ')")
    public List<RequestListElementDto> getAllRequests(String type) {
        List<Request> requests = requestRepository.findAll().stream().filter(x -> x.getRequestType().name().equals(type)).collect(Collectors.toList());
        List<RequestListElementDto> requestListElementDtos = new ArrayList<>();
        for (Request a : requests) {
            if (!a.isDeleted()) {
                requestListElementDtos.add(RequestConverter.toRequestListElement(a));
            }
        }
        return requestListElementDtos;
    }

    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('REQUEST_LIST_READ')")
    public List<RequestListElementDto> getAllRequestsForUser(String username) {
        List<Request> requests = requestRepository.findAll().stream().filter(x -> x.getUser().getUsername().equals(username)).collect(Collectors.toList());
        List<RequestListElementDto> requestListElementDtos = new ArrayList<>();
        for (Request a : requests) {
            if (!a.isDeleted()) {
                requestListElementDtos.add(RequestConverter.toRequestListElement(a));
            }
        }
        return requestListElementDtos;
    }

    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('REQUEST_LIST_READ')")
    public List<RequestListElementDto> getAllRequestForManager(String username) {
        User user = userRepository.find(username);
        List<Request> requests = requestRepository.findAll().stream().filter(x -> x.getUser().getOffice().getId().equals(user.getOffice().getId()) && x.getStatus().name().equals(Status.WAITING.name()))
                .collect(Collectors.toList());
        List<RequestListElementDto> requestListElementDtos = new ArrayList<>();
        for (Request a : requests) {
            if (!a.isDeleted()) {
                requestListElementDtos.add(RequestConverter.toRequestListElement(a));
            }
        }
        return requestListElementDtos;
    }


    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('REQUEST_LIST_READ')")
    public List<RequestListElementDto> getAllRequestFromeOtherUsers(String username) {
        User user = userRepository.find(username);
        List<Request> requests = requestRepository.findAll().stream().filter(x -> !x.getUser().getUsername().equals(username) && x.getStatus().name().equals(Status.IN_WAREHOUSE.name())
                && x.getSenderWarehouse().getUser().getId().equals(user.getId()) || x.getRecieverWarehouse().getUser().getId().equals(user.getId())).collect(Collectors.toList());
        List<RequestListElementDto> requestListElementDtos = new ArrayList<>();
        for (Request a : requests) {
            if (!a.isDeleted()) {
                requestListElementDtos.add(RequestConverter.toRequestListElement(a));
            }
        }
        return requestListElementDtos;
    }

    @Override
    public List<RequestListElementDto> getAllRequestForWarehouseman(String username) {
        User user = userRepository.find(username);
        List<Request> requests = requestRepository.findAll().stream().filter(x -> x.getUser().getUsername().equals(username)
                && (x.getRecieverWarehouse().getWarehouseType().name().equals(WarehouseType.OFFICE.name()) || x.getSenderWarehouse().getWarehouseType().name().equals(WarehouseType.OFFICE.name())))
                .collect(Collectors.toList());
        List<RequestListElementDto> requestListElementDtos = new ArrayList<>();
        for (Request a : requests) {
            if (!a.isDeleted()) {
                requestListElementDtos.add(RequestConverter.toRequestListElement(a));
            }
        }
        return requestListElementDtos;
    }

    @Override
    public List<RequestListElementDto> getAllRequestFromOtherWarehouses(String username) {
        User user = userRepository.find(username);
        List<Request> requests = requestRepository.findAll().stream().filter(x -> !x.getUser().getUsername().equals(username) && x.getStatus().name().equals(Status.WAITNING_FOR_DELIVERY.name())).collect(Collectors.toList());
        List<RequestListElementDto> requestListElementDtos = new ArrayList<>();
        for (Request a : requests) {
            if (!a.isDeleted()) {
                requestListElementDtos.add(RequestConverter.toRequestListElement(a));
            }
        }
        return requestListElementDtos;
    }

    @Override
    public List<RequestListElementDto> getAllRequestsForOffice(String type, Long id) {
        List<Request> requests = requestRepository.findAll().stream().filter(x -> x.getRequestType().name().equals(type) && x.getRecieverWarehouse().getOffice().getId().equals(id)
                || x.getSenderWarehouse().getOffice().getId().equals(id)).collect(Collectors.toList());
        List<RequestListElementDto> requestListElementDtos = new ArrayList<>();
        for (Request a : requests) {
            if (!a.isDeleted()) {
                requestListElementDtos.add(RequestConverter.toRequestListElement(a));
            }
        }
        return requestListElementDtos;
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('REQUEST_CREATE')")
    public void createNewRequest(Request request) throws ServiceException {
        try {
            requestRepository.create(request);

           for(Device d : request.getDevices()){
               d.setStatus(DeviceStatus.RESERVED);
               deviceRepository.update(d);
           }
        } catch (DatabaseErrorException e) {
            throw e;
        } catch (EntityException e) {
            throw ServiceException.createServiceException(ServiceException.ENTITY_VALIDATION, e);
        }
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('REQUEST_UPDATE')")
    public void updateRequest(Request request) throws ServiceException {
        requestRepository.update(request);
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('REQUEST_DELETE')")
    public void deleteRequestById(Long id) {
        requestRepository.find(id).setDeleted(true);
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('REQUEST_DELETE')")
    public void deleteRequest(Request request) {
        requestRepository.find(request.getId()).setDeleted(true);
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('REQUEST_UPDATE')")
    public void realizeRequest(ChangeStatusDto changeStatusDto) throws ServiceException {
        Request request = requestRepository.find(changeStatusDto.getId());
        request.setVersion(changeStatusDto.getVersion());
        request.setStatus(Status.valueOf(changeStatusDto.getStatus()));
        requestRepository.update(request);

        switch (request.getRequestType()) {

            case TRANSFER_REQUEST:
                if (Status.ACCEPTED.name().equals(changeStatusDto.getStatus())) {

                    Transfer transfer = new Transfer();
                    for (Device d : request.getDevices()) {
                        d.setStatus(DeviceStatus.REPOSE);
                        d.setWarehouse(request.getRecieverWarehouse());
                        transfer.setDevice(d);
                        deviceRepository.update(d);
                    }
                    transfer.setRecieverWarehouse(request.getRecieverWarehouse());
                    transfer.setSenderWarehouse(request.getSenderWarehouse());
                    transfer.setStatus(Status.TRANSFERED);
                    transfer.setTitle(request.getTitle());
                    transfer.setTransferDate(Calendar.getInstance());
                    transfer.setDeleted(false);
                    transfer.setDescription(request.getDescription());
                    transfer.setUsername(request.getUser().getUsername());
                    transferRepository.create(transfer);
                    request.setStatus(Status.REALIZED);
                    requestRepository.update(request);
                }

                break;
            case DEVICE_REQUEST:
                if(Status.ACCEPTED.name().equals(changeStatusDto.getStatus())){
                    request.setStatus(Status.IN_WAREHOUSE);
                    requestRepository.update(request);

                } else if (Status.IN_WAREHOUSE.name().equals(changeStatusDto.getStatus())) {
                    for (Device d : request.getDevices()) {
                        d.setStatus(DeviceStatus.RESERVED);
                        deviceRepository.update(d);
                    }
                } else if(Status.TO_TAKE_AWAY.name().equals(changeStatusDto.getStatus())){
                    Transfer transfer = new Transfer();
                    transfer.setRecieverWarehouse(request.getRecieverWarehouse());
                    transfer.setSenderWarehouse(request.getSenderWarehouse());
                    transfer.setStatus(Status.TRANSFERED);
                    transfer.setTitle(request.getTitle());
                    transfer.setTransferDate(Calendar.getInstance());
                    transfer.setDeleted(false);
                    transfer.setDescription(request.getDescription());
                    transfer.setUsername(request.getUser().getUsername());

                    for (Device d : request.getDevices()) {
                        d.setStatus(DeviceStatus.REPOSE);
                        d.setWarehouse(request.getRecieverWarehouse());
                        transfer.setDevice(d);
                        transferRepository.create(transfer);
                        deviceRepository.update(d);
                    }
                    request.setStatus(Status.REALIZED);
                    requestRepository.update(request);
                }

        }

    }

    @Override
    public List<DeviceListElementDto> getAllRequestDevices(Long id) {
        Request request=requestRepository.find(id);
        List<DeviceListElementDto> deviceListElementDtos = new ArrayList<>();
        for (Device a : request.getDevices()) {
                deviceListElementDtos.add(DeviceConverter.toDeviceListElementDto(a));
        }
        return deviceListElementDtos;
    }

    @Override
    public void addDevicesToRequest(List<Long> devices, Long requestId)throws ServiceException {
       Request request= requestRepository.find(requestId);
        List<Device> deviceList=new ArrayList<>();
        for(Long d : devices){
            deviceList.add(deviceRepository.find(d));
        }
        request.setDevices(deviceList);
        requestRepository.update(request);
    }

    Notification transferRequestNotifiction(Request request){
        Notification notification=new Notification();
        notification.setUser(request.getUser());
        notification.setDeleted(false);
        notification.setReaded(false);
        notification.setTitle(request.getTitle());
        notification.setDescription(request.getDescription());
       return notification;
    }



}
