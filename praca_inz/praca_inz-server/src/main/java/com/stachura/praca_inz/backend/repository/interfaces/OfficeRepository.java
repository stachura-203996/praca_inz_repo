package com.stachura.praca_inz.backend.repository.interfaces;

import com.stachura.praca_inz.backend.exception.EntityException;
import com.stachura.praca_inz.backend.model.Office;

import java.util.List;

public interface OfficeRepository {
    Office find(Long id);

    List<Office> findAll();

    void create(Office office)throws EntityException;

    Office update(Office office)throws EntityException;

    void remove(Long id);

    void remove(Office office);
}
