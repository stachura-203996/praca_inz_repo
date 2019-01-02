package com.stachura.praca_inz.backend.repository.interfaces;

import com.stachura.praca_inz.backend.exception.repository.EntityException;
import com.stachura.praca_inz.backend.model.Request;

import java.util.List;

public interface RequestRepository {

    Request find(Long id);

    List<Request> findAll();

    void create(Request request)throws EntityException;

    void update(Request request)throws EntityException;

    void remove(Long id);

    void remove(Request request);
}
