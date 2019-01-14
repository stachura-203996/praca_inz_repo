package com.stachura.praca_inz.backend.service.impl;

import com.google.common.collect.Lists;
import com.stachura.praca_inz.backend.Constants;
import com.stachura.praca_inz.backend.exception.repository.DatabaseErrorException;
import com.stachura.praca_inz.backend.exception.repository.EntityException;
import com.stachura.praca_inz.backend.exception.service.ServiceException;
import com.stachura.praca_inz.backend.model.*;
import com.stachura.praca_inz.backend.model.enums.DeviceStatus;
import com.stachura.praca_inz.backend.model.enums.Status;
import com.stachura.praca_inz.backend.model.enums.WarehouseType;
import com.stachura.praca_inz.backend.model.security.User;
import com.stachura.praca_inz.backend.model.security.UserRole;
import com.stachura.praca_inz.backend.repository.*;
import com.stachura.praca_inz.backend.service.NotificationService;
import com.stachura.praca_inz.backend.service.RequestService;
import com.stachura.praca_inz.backend.web.dto.converter.DeviceConverter;
import com.stachura.praca_inz.backend.web.dto.device.DeviceListElementDto;
import com.stachura.praca_inz.backend.web.dto.request.ChangeStatusDto;
import com.stachura.praca_inz.backend.web.dto.request.DeviceRequestAddDto;
import com.stachura.praca_inz.backend.web.dto.request.RequestListElementDto;
import com.stachura.praca_inz.backend.web.dto.converter.RequestConverter;
import com.stachura.praca_inz.backend.web.dto.request.TransferRequestAddDto;
import com.stachura.praca_inz.backend.web.utils.NotificationMessages;
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
    private DeviceModelRepository deviceModelRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TransferRepository transferRepository;

    @Autowired
    private WarehouseRepository warehouseRepository;

    @Autowired
    private NotificationService notificationService;

    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('REQUEST_READ')")
    public Request getRequestById(Long id) throws ServiceException {
        Request request = requestRepository.findById(id).orElseThrow(() -> new ServiceException());
        if (request.isDeleted()) {
            return null;
        }
        return request;
    }

    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('REQUEST_LIST_READ')")
    public List<RequestListElementDto> getAllRequests(String type,String username) throws ServiceException {
        List<Request> requests;
        User user=userRepository.findByUsername(username).orElseThrow(()->new ServiceException());
        if(user.getUserRoles().stream().anyMatch(x->x.getName().equals(Constants.ADMIN_ROLE))) {
            requests = Lists.newArrayList(requestRepository.findAll()).stream().filter(x -> x.getRequestType().name().equals(type)).collect(Collectors.toList());
        } else{
            requests = Lists.newArrayList(requestRepository.findAll()).stream().filter(x -> x.getRequestType().name().equals(type) && x.getUser().getOffice().getDepartment().getCompany().getId()
                    .equals(user.getOffice().getDepartment().getCompany().getId())).collect(Collectors.toList());
        }
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
        List<Request> requests = Lists.newArrayList(requestRepository.findAll()).stream().filter(x -> x.getUser().getUsername().equals(username)).collect(Collectors.toList());
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
    public List<RequestListElementDto> getAllRequestForManager(String username) throws ServiceException {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new ServiceException());
        List<Request> requests = Lists.newArrayList(requestRepository.findAll()).stream().filter(x -> x.getUser().getOffice().getId().equals(user.getOffice().getId()) && x.getStatus().name().equals(Status.WAITING.name()))
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
    public List<RequestListElementDto> getAllRequestFromeOtherUsers(String username) throws ServiceException {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new ServiceException());
        List<Request> requests = Lists.newArrayList(requestRepository.findAll()).stream().filter(x ->x.getStatus().equals(Status.IN_WAREHOUSE)
                && x.getUser().getOffice().getId().equals(user.getOffice().getId())).collect(Collectors.toList());
        List<RequestListElementDto> requestListElementDtos = new ArrayList<>();
        for (Request a : requests) {
            if (!a.isDeleted()) {
                requestListElementDtos.add(RequestConverter.toRequestListElement(a));
            }
        }
        return requestListElementDtos;
    }

    @Override
    public List<RequestListElementDto> getAllRequestForWarehouseman(String username) throws ServiceException {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new ServiceException());
        List<Request> requests = Lists.newArrayList(requestRepository.findAll()).stream().filter(x -> x.getUser().getUsername().equals(username)
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
    public List<RequestListElementDto> getAllRequestFromOtherWarehouses(String username) throws ServiceException {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new ServiceException());
        List<Request> requests = Lists.newArrayList(requestRepository.findAll()).stream().filter(x -> !x.getUser().getUsername().equals(username) && x.getStatus().name().equals(Status.WAITNING_FOR_DELIVERY.name())).collect(Collectors.toList());
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
        List<Request> requests = Lists.newArrayList(requestRepository.findAll()).stream().filter(x -> x.getRequestType().name().equals(type) && x.getRecieverWarehouse().getOffice().getId().equals(id)
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
    public void createNewTransferRequest(TransferRequestAddDto transferRequestAddDto, String username) throws ServiceException {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new ServiceException());
        Warehouse reciever = warehouseRepository.findById(transferRequestAddDto.getRecieverWarehouseId()).orElseThrow(() -> new ServiceException());
        Device device = deviceRepository.findById(transferRequestAddDto.getDeviceId()).orElseThrow(() -> new ServiceException());
        Request request=RequestConverter.toRequest(transferRequestAddDto, device, reciever, user);
        List<User> managers= Lists.newArrayList(userRepository.findAll()).stream().filter(x->x.getUserRoles().stream().filter(z->z.getName().equals("MANAGER")).findFirst().isPresent()
                && x.getOffice().getId().equals(user.getOffice().getId())).collect(Collectors.toList());
        for(User m:managers){
            notificationService.createNewNotification(NotificationMessages.getRequestReceivedManagerNotifiaction(request,m));
        }
       notificationService.createNewNotification(NotificationMessages.getRequestSentNotifiaction(request,user));
        requestRepository.save(request);

        for (Device d : request.getDevices()) {
            d.setStatus(DeviceStatus.RESERVED);
            deviceRepository.save(d);
        }
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('REQUEST_CREATE')")
    public void createNewDeviceRequest(DeviceRequestAddDto deviceRequestAddDto, String username) throws ServiceException {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new ServiceException());
        DeviceModel device = deviceModelRepository.findById(deviceRequestAddDto.getDeviceModelId()).orElseThrow(() -> new ServiceException());
        Warehouse warehouse=Lists.newArrayList(warehouseRepository.findAll()).stream().filter(x->x.getWarehouseType().equals(WarehouseType.USER)&&x.getUser().getUsername().equals(username)).findFirst().orElseThrow(()->new ServiceException());
        Request request=RequestConverter.toRequest(deviceRequestAddDto, device,warehouse, user);
        requestRepository.save(request);

        for (Device d : request.getDevices()) {
            d.setStatus(DeviceStatus.RESERVED);
            deviceRepository.save(d);
        }
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('REQUEST_UPDATE')")
    public void updateRequest(Request request) throws ServiceException {
        requestRepository.save(request);
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('REQUEST_DELETE')")
    public void deleteRequestById(Long id) throws ServiceException {
        requestRepository.findById(id).orElseThrow(() -> new ServiceException()).setDeleted(true);
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('REQUEST_UPDATE')")
    public void realizeRequest(ChangeStatusDto changeStatusDto) throws ServiceException {
        Request request = requestRepository.findById(changeStatusDto.getId()).orElseThrow(() -> new ServiceException());
        request.setVersion(changeStatusDto.getVersion());
        request.setStatus(Status.valueOf(changeStatusDto.getStatus()));
        requestRepository.save(request);

        switch (request.getRequestType()) {

            case TRANSFER_REQUEST:
                if (Status.ACCEPTED.name().equals(changeStatusDto.getStatus())) {

                    Transfer transfer = new Transfer();
                    for (Device d : request.getDevices()) {
                        d.setStatus(DeviceStatus.REPOSE);
                        d.setWarehouse(request.getRecieverWarehouse());
                        transfer.setDevice(d);
                        deviceRepository.save(d);
                    }
                    transfer.setRecieverWarehouse(request.getRecieverWarehouse());
                    transfer.setSenderWarehouse(request.getSenderWarehouse());
                    transfer.setStatus(Status.TRANSFERED);
                    transfer.setTitle(request.getTitle());
                    transfer.setTransferDate(Calendar.getInstance());
                    transfer.setDeleted(false);
                    transfer.setDescription(request.getDescription());
                    transfer.setUsername(request.getUser().getUsername());
                    transferRepository.save(transfer);
                    request.setStatus(Status.REALIZED);
                    requestRepository.save(request);
                }

                break;
            case DEVICE_REQUEST:
                if (Status.ACCEPTED.name().equals(changeStatusDto.getStatus())) {
                    request.setStatus(Status.IN_WAREHOUSE);
                    requestRepository.save(request);

                } else if (Status.IN_WAREHOUSE.name().equals(changeStatusDto.getStatus())) {
                    for (Device d : request.getDevices()) {
                        d.setStatus(DeviceStatus.RESERVED);
                        deviceRepository.save(d);
                    }
                } else if (Status.TO_TAKE_AWAY.name().equals(changeStatusDto.getStatus())) {
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
                        transferRepository.save(transfer);
                        deviceRepository.save(d);
                    }
                    request.setStatus(Status.REALIZED);
                    requestRepository.save(request);
                }

        }

    }

    @Override
    public List<DeviceListElementDto> getAllRequestDevices(Long id) throws ServiceException {
        Request request = requestRepository.findById(id).orElseThrow(() -> new ServiceException());
        List<DeviceListElementDto> deviceListElementDtos = new ArrayList<>();
        for (Device a : request.getDevices()) {
            deviceListElementDtos.add(DeviceConverter.toDeviceListElementDto(a));
        }
        return deviceListElementDtos;
    }

    @Override
    public void addDevicesToRequest(List<Long> devices, Long requestId) throws ServiceException {
        Request request = requestRepository.findById(requestId).orElseThrow(() -> new ServiceException());
        List<Device> deviceList = new ArrayList<>();
        for (Long d : devices) {
            deviceList.add(deviceRepository.findById(d).orElseThrow(() -> new ServiceException()));
        }
        request.setDevices(deviceList);
        requestRepository.save(request);
    }


}
