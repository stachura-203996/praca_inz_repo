package com.stachura.praca_inz.backend.repository.interfaces;

import com.stachura.praca_inz.backend.exception.EntityException;
import com.stachura.praca_inz.backend.model.DeviceRequest;
import java.util.List;

public interface DeviceRequestRepository {
    DeviceRequest find(Long id);

    List<DeviceRequest> findAll();

    void create(DeviceRequest office)throws EntityException;

    DeviceRequest update(DeviceRequest office)throws EntityException;

    void remove(Long id);

    void remove(DeviceRequest office);
}
