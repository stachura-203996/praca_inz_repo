package com.stachura.praca_inz.backend.service;

import com.stachura.praca_inz.backend.exception.base.AppBaseException;
import com.stachura.praca_inz.backend.model.Office;
import com.stachura.praca_inz.backend.web.dto.company.CompanyStructureAddDto;
import com.stachura.praca_inz.backend.web.dto.company.CompanyStructureEditDto;
import com.stachura.praca_inz.backend.web.dto.company.CompanyStructuresListElementDto;

import java.util.List;

public interface OfficeService {
    Office getOfficeById(Long id) throws AppBaseException;

    List<CompanyStructuresListElementDto> getAllOfficesForCompany(Long id);

    List<CompanyStructuresListElementDto> getAllOfficesForDepartment(Long id);

    List<CompanyStructuresListElementDto> getAll(String username) throws AppBaseException;

    void create(CompanyStructureAddDto companyStructureAddDto) throws AppBaseException;

    void update(CompanyStructureEditDto companyStructureEditDto) throws AppBaseException;

    void delete(Long id) throws AppBaseException;

}
