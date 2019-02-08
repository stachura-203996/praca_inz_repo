package com.stachura.praca_inz.backend.service;

import com.stachura.praca_inz.backend.exception.EntityNotInDatabaseException;
import com.stachura.praca_inz.backend.exception.base.SystemBaseException;
import com.stachura.praca_inz.backend.web.dto.company.CompanyStructureAddDto;
import com.stachura.praca_inz.backend.web.dto.company.CompanyStructureEditDto;
import com.stachura.praca_inz.backend.web.dto.company.CompanyStructureViewDto;
import com.stachura.praca_inz.backend.web.dto.company.CompanyStructuresListElementDto;

import java.util.List;

public interface OfficeService {
    CompanyStructureViewDto getOfficeToView(Long id) throws SystemBaseException;

    List<CompanyStructuresListElementDto> getAllOfficesForCompany(Long id);

    List<CompanyStructuresListElementDto> getAllOfficesForDepartment(Long id);

    List<CompanyStructuresListElementDto> getAll(String username) throws SystemBaseException;

    void create(CompanyStructureAddDto companyStructureAddDto) throws SystemBaseException;

    void update(CompanyStructureEditDto companyStructureEditDto) throws SystemBaseException;

    void delete(Long id) throws SystemBaseException;

    CompanyStructureEditDto getOfficeToEdit(Long id) throws EntityNotInDatabaseException;
}
