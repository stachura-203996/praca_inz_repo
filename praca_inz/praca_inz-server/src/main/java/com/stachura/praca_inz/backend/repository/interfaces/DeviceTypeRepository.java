package com.stachura.praca_inz.backend.repository.interfaces;

import com.stachura.praca_inz.backend.exception.repository.EntityException;
import com.stachura.praca_inz.backend.model.DeviceType;


import java.util.List;

public interface DeviceTypeRepository {

    DeviceType find(Long id);


    List<DeviceType> findAll();

    void create(DeviceType company) throws EntityException;

    void update(DeviceType company) throws EntityException;

    void remove(Long id);

    void remove(DeviceType company);
}
