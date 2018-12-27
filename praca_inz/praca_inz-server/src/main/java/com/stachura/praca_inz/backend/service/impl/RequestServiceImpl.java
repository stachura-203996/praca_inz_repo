package com.stachura.praca_inz.backend.service.impl;

import com.stachura.praca_inz.backend.exception.EntityException;
import com.stachura.praca_inz.backend.model.Request;
import com.stachura.praca_inz.backend.repository.interfaces.RequestRepository;
import com.stachura.praca_inz.backend.service.RequestService;
import com.stachura.praca_inz.backend.web.dto.RequestListElementDto;
import com.stachura.praca_inz.backend.web.dto.converter.RequestConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

public class RequestServiceImpl implements RequestService {

    @Autowired
    private RequestRepository requestRepository;

    @Override
    @Transactional(readOnly = true)
//    @PreAuthorize("hasAuthority('REQUEST_READ')")
    public Request getRequestById(Long id) {
        Request request= requestRepository.find(id);
        if(request.isDeleted()){
            return null;
        }
        return request;
    }

    @Override
    @Transactional(readOnly = true)
//    @PreAuthorize("hasAuthority('REQUEST_LIST_READ')")
    public List<RequestListElementDto> getAllRequests() {
        List<Request> requests = requestRepository.findAll();
        List<RequestListElementDto> requestListElementDtos = new ArrayList<>();
        for (Request a : requests) {
            if(!a.isDeleted()) {
                requestListElementDtos.add(RequestConverter.toRequestListElement(a));
            }
        }
        return requestListElementDtos;
    }

    //TODO
    @Override
    @Transactional
//    @PreAuthorize("hasAuthority('REQUEST_CREATE')")
    public void createNewRequest(Request request) {
        try {
            requestRepository.create(request);

        } catch (EntityException e) {

        }

    }

    //TODO
    @Override
    @Transactional
//    @PreAuthorize("hasAuthority('REQUEST_UPDATE')")
    public Request updateRequest(Request request) {
        Request tmp = new Request();
        try {
            if (!requestRepository.find(request.getId()).isDeleted()) {
                tmp = requestRepository.update(request);
            }
        } catch (EntityException e) {

        }
        return tmp;
    }

    @Override
    @Transactional
//    @PreAuthorize("hasAuthority('REQUEST_DELETE')")
    public void deleteRequestById(Long id) {
        requestRepository.find(id).setDeleted(true);
    }

    @Override
    @Transactional
//    @PreAuthorize("hasAuthority('REQUEST_DELETE')")
    public void deleteRequest(Request request) {
        requestRepository.find(request.getId()).setDeleted(true);
    }
}
