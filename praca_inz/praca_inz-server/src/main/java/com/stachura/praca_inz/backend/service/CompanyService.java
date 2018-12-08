package com.stachura.praca_inz.backend.service;

import com.stachura.praca_inz.backend.model.Company;
import com.stachura.praca_inz.backend.web.dto.CompanyStructuresListElementDto;

import java.util.List;

public interface CompanyService {

    Company get(Long id);

    Company get(String name);

    List<CompanyStructuresListElementDto> getAll();

    void create(Company company);

    Company update(Company company);

    void delete(Long id);

    void delete(Company company);
}
