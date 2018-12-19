package com.stachura.praca_inz.backend.repository.interfaces;

import com.stachura.praca_inz.backend.exception.EntityException;
import com.stachura.praca_inz.backend.model.Warehouse;

import java.util.List;

public interface WarehouseRepository {

    Warehouse find(Long id);

    List<Warehouse> findAll();

    void create(Warehouse company)throws EntityException;

    Warehouse update(Warehouse company)throws EntityException;

    void remove(Long id);

    void remove(Warehouse company);
}
