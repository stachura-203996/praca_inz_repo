package com.stachura.praca_inz.backend.repository.interfaces;

import com.stachura.praca_inz.backend.exception.EntityException;
import com.stachura.praca_inz.backend.model.DeviceType;
import java.util.List;

public interface DeviceTypeRepository {

    DeviceType find(Long id);

    List<DeviceType> findAll();

    void create(DeviceType office)throws EntityException;

    DeviceType update(DeviceType office)throws EntityException;

    void remove(Long id);

    void remove(DeviceType office);
}
