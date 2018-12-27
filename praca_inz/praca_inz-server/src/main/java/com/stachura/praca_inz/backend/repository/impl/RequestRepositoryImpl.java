package com.stachura.praca_inz.backend.repository.impl;

import com.stachura.praca_inz.backend.model.Request;
import com.stachura.praca_inz.backend.repository.AbstractRepository;
import com.stachura.praca_inz.backend.repository.interfaces.RequestRepository;
import org.springframework.stereotype.Repository;

@Repository
public class RequestRepositoryImpl extends AbstractRepository<Request> implements RequestRepository {

    protected RequestRepositoryImpl() {
        super(Request.class);
    }
}
