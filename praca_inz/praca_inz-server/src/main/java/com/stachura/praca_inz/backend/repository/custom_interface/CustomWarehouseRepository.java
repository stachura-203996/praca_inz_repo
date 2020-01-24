package com.stachura.praca_inz.backend.repository.custom_interface;

import com.stachura.praca_inz.backend.model.Warehouse;

public interface CustomWarehouseRepository {
    void detach(Warehouse entity);
}
