package com.stachura.praca_inz.backend.repository.impl;

import com.stachura.praca_inz.backend.model.SystemMessage;
import com.stachura.praca_inz.backend.repository.AbstractRepository;
import com.stachura.praca_inz.backend.repository.interfaces.SystemMessageRepository;
import org.springframework.stereotype.Repository;

@Repository
public class SystemMessageRepositoryImpl extends AbstractRepository<SystemMessage> implements SystemMessageRepository {

    public SystemMessageRepositoryImpl() {
        super(SystemMessage.class);
    }
}
