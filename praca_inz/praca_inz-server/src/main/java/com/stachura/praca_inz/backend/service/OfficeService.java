package com.stachura.praca_inz.backend.service;

import com.stachura.praca_inz.backend.model.Office;

import java.util.List;

public interface OfficeService {
    Office get(Long id);

    Office get(String name);

    List<Office> getAll();

    void create(Office office);

    Office update(Office office);

    void delete(Long id);

    void delete(Office office);
}
