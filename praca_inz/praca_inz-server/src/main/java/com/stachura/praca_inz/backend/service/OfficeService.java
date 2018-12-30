package com.stachura.praca_inz.backend.service;

import com.stachura.praca_inz.backend.model.Office;
import com.stachura.praca_inz.backend.web.dto.CompanyStructuresListElementDto;

import java.util.List;

public interface OfficeService {
    Office getOfficeById(Long id);

//    Office getCompanyById(String name);

    List<CompanyStructuresListElementDto> getAllOfficesForCompany(Long id);

    List<CompanyStructuresListElementDto> getAllOfficesForDepartment(Long id);

    List<CompanyStructuresListElementDto> getAll();

    void create(Office office);

    Office update(Office office);

    void delete(Long id);

    void delete(Office office);
}
