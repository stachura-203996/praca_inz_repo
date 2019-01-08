package com.stachura.praca_inz.backend.repository.interfaces;

import com.stachura.praca_inz.backend.exception.repository.EntityException;
import com.stachura.praca_inz.backend.model.SystemMessage;

import java.util.List;

public interface SystemMessageRepository {

    SystemMessage find(Long id);

     List<SystemMessage> findAll();

    void create(SystemMessage systemMessage)throws EntityException;

    void update(SystemMessage systemMessage)throws EntityException;

    void remove(Long id);

    void remove(SystemMessage systemMessage);
}
