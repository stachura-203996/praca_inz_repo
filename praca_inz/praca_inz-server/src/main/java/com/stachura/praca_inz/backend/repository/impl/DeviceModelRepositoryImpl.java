package com.stachura.praca_inz.backend.repository.impl;

import com.stachura.praca_inz.backend.model.DeviceModel;
import com.stachura.praca_inz.backend.repository.AbstractRepository;
import com.stachura.praca_inz.backend.repository.interfaces.DeviceModelRepository;
import org.springframework.stereotype.Repository;

@Repository
public class DeviceModelRepositoryImpl extends AbstractRepository<DeviceModel> implements DeviceModelRepository {

    public DeviceModelRepositoryImpl() {
        super(DeviceModel.class);
    }
}
