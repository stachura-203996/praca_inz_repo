package com.stachura.praca_inz.backend.repository.interfaces;

import com.stachura.praca_inz.backend.exception.EntityException;
import com.stachura.praca_inz.backend.model.TransferRequest;

import java.util.List;

public interface TransferRequestRepository {

    TransferRequest find(Long id);

//    TransferRequest find(String name);

    List<TransferRequest> findAll();

    void create(TransferRequest company)throws EntityException;

    TransferRequest update(TransferRequest company)throws EntityException;

    void remove(Long id);

    void remove(TransferRequest company);
}
