package com.stachura.praca_inz.backend.repository.custom_interface;

import com.stachura.praca_inz.backend.model.Device;

public interface CustomDeviceRepository {
    void detach(Device entity);
}
