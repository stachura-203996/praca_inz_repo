package com.stachura.praca_inz.backend.repository.impl;

import com.stachura.praca_inz.backend.model.SystemMessageType;
import com.stachura.praca_inz.backend.repository.AbstractRepository;
import com.stachura.praca_inz.backend.repository.interfaces.SystemMessageTypeRepository;
import org.springframework.stereotype.Repository;

@Repository
public class SystemMessageTypeRepositoryImpl extends AbstractRepository<SystemMessageType> implements SystemMessageTypeRepository {

    protected SystemMessageTypeRepositoryImpl() {super(SystemMessageType.class);}
}
