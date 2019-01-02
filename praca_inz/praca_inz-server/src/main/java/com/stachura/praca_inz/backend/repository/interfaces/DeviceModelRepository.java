package com.stachura.praca_inz.backend.repository.interfaces;

import com.stachura.praca_inz.backend.exception.repository.EntityException;
import com.stachura.praca_inz.backend.model.DeviceModel;

import java.util.List;

public interface DeviceModelRepository {

    DeviceModel find(Long id);

    List<DeviceModel> findAll();

    void create(DeviceModel office)throws EntityException;

    void update(DeviceModel office)throws EntityException;

    void remove(Long id);

    void remove(DeviceModel office);
}
