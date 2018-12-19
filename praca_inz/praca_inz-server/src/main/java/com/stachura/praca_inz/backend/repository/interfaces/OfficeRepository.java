package com.stachura.praca_inz.backend.repository.interfaces;

import com.stachura.praca_inz.backend.model.Office;

import java.util.List;

public interface OfficeRepository {
    Office find(Long id);

    Office find(String name);

    List<Office> findAll();

    void create(Office office);

    Office update(Office office);

    void delete(Long id);

    void delete(Office office);
}
