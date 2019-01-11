package com.stachura.praca_inz.backend.service;

import com.stachura.praca_inz.backend.exception.service.ServiceException;
import com.stachura.praca_inz.backend.model.Company;
import com.stachura.praca_inz.backend.web.dto.company.CompanyStructureAddDto;
import com.stachura.praca_inz.backend.web.dto.company.CompanyStructureEditDto;
import com.stachura.praca_inz.backend.web.dto.company.CompanyStructuresListElementDto;

import java.util.List;

public interface CompanyService {

    Company getCompanyById(Long id) throws ServiceException;

    List<CompanyStructuresListElementDto> getAllCompanies();

    Long createNewCompany(CompanyStructureAddDto company) throws ServiceException;

    void updateCompany(CompanyStructureEditDto companyStructureEditDto) throws ServiceException;

    void deleteCompanyById(Long id) throws ServiceException;

}
