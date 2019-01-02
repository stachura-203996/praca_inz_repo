package com.stachura.praca_inz.backend.repository.interfaces;

import com.stachura.praca_inz.backend.exception.repository.EntityException;
import com.stachura.praca_inz.backend.model.Device;

import java.util.List;

public interface DeviceRepository {
    Device find(Long id);

    List<Device> findAll();

    void create(Device company)throws EntityException;

    void update(Device company)throws EntityException;

    void remove(Long id);

    void remove(Device company);
}
