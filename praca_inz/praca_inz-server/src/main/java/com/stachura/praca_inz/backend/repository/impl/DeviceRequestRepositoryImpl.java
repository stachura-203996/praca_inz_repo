package com.stachura.praca_inz.backend.repository.impl;

import com.stachura.praca_inz.backend.model.DeviceRequest;
import com.stachura.praca_inz.backend.repository.AbstractRepository;
import com.stachura.praca_inz.backend.repository.interfaces.DeviceRequestRepository;
import org.springframework.stereotype.Repository;

@Repository
public class DeviceRequestRepositoryImpl extends AbstractRepository<DeviceRequest> implements DeviceRequestRepository {

    public DeviceRequestRepositoryImpl() {
        super(DeviceRequest.class);
    }
}
