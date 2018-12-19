package com.stachura.praca_inz.backend.repository.interfaces;

import com.stachura.praca_inz.backend.exception.EntityException;
import com.stachura.praca_inz.backend.model.SystemMessageType;


import java.util.List;

public interface SystemMessageTypeRepository {

    SystemMessageType find(Long id);

    List<SystemMessageType> findAll();

    void create(SystemMessageType systemMessage)throws EntityException;

    SystemMessageType update(SystemMessageType systemMessage)throws EntityException;

    void remove(Long id);

    void remove(SystemMessageType systemMessage);
}
