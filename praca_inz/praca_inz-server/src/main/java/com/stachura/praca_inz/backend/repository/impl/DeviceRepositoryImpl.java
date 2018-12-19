package com.stachura.praca_inz.backend.repository.impl;

import com.stachura.praca_inz.backend.model.Device;
import com.stachura.praca_inz.backend.model.Office;
import com.stachura.praca_inz.backend.repository.AbstractRepository;
import com.stachura.praca_inz.backend.repository.interfaces.DeviceRepository;
import org.springframework.stereotype.Repository;

@Repository
public class DeviceRepositoryImpl extends AbstractRepository<Device> implements DeviceRepository {
    public DeviceRepositoryImpl() {
        super(Device.class);
    }
}
