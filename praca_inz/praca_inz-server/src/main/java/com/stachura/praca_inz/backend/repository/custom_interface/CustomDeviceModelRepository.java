package com.stachura.praca_inz.backend.repository.custom_interface;

import com.stachura.praca_inz.backend.model.DeviceModel;

public interface CustomDeviceModelRepository {

    void detach(DeviceModel entity);
}
