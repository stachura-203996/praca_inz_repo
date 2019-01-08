package com.stachura.praca_inz.backend.repository.interfaces;

import com.stachura.praca_inz.backend.exception.repository.EntityException;
import com.stachura.praca_inz.backend.model.Warehouse;

import java.util.List;

public interface WarehouseRepository {

    Warehouse find(Long id);

    Warehouse findUserWarehouse(Long userId);

    Warehouse findOfficeWarehouse(Long userId);

    List<Warehouse> findAll();

    void create(Warehouse warehouse)throws EntityException;

    void update(Warehouse warehouse)throws EntityException;

    void remove(Long id);

    void remove(Warehouse warehouse);
}
