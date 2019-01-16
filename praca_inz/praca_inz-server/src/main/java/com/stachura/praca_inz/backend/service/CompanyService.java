package com.stachura.praca_inz.backend.service;

import com.stachura.praca_inz.backend.exception.EntityNotInDatabaseException;
import com.stachura.praca_inz.backend.exception.base.AppBaseException;
import com.stachura.praca_inz.backend.model.Company;
import com.stachura.praca_inz.backend.web.dto.company.CompanyStructureAddDto;
import com.stachura.praca_inz.backend.web.dto.company.CompanyStructureEditDto;
import com.stachura.praca_inz.backend.web.dto.company.CompanyStructureViewDto;
import com.stachura.praca_inz.backend.web.dto.company.CompanyStructuresListElementDto;

import java.util.List;

public interface CompanyService {

    CompanyStructureViewDto getCompanyToView(Long id) throws EntityNotInDatabaseException;

    CompanyStructureEditDto getCompanyToEdit(Long id) throws EntityNotInDatabaseException;

    List<CompanyStructuresListElementDto> getAllCompanies();

    void createNewCompany(CompanyStructureAddDto company) throws AppBaseException;

    void updateCompany(CompanyStructureEditDto companyStructureEditDto) throws  AppBaseException;

    void deleteCompanyById(Long id) throws  AppBaseException;

}
