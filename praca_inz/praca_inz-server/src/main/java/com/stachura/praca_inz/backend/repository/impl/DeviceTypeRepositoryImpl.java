package com.stachura.praca_inz.backend.repository.impl;

import com.stachura.praca_inz.backend.model.DeviceType;
import com.stachura.praca_inz.backend.repository.AbstractRepository;
import com.stachura.praca_inz.backend.repository.interfaces.DeviceTypeRepository;
import org.springframework.stereotype.Repository;

@Repository
public class DeviceTypeRepositoryImpl extends AbstractRepository<DeviceType> implements DeviceTypeRepository {

    public DeviceTypeRepositoryImpl() {
        super(DeviceType.class);
    }
}
