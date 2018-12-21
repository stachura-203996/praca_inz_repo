package com.stachura.praca_inz.backend.service;

import com.stachura.praca_inz.backend.model.Company;
import com.stachura.praca_inz.backend.web.dto.CompanyStructuresListElementDto;

import java.util.List;

public interface CompanyService {

    Company getCompanyById(Long id);

    Company getCompanyByName(String name);

    List<CompanyStructuresListElementDto> getAllCompanies();

    void createNewCompany(Company company);

    Company updateCompany(Company company);

    void deleteCompanyById(Long id);

    void deleteCompany(Company company);
}
