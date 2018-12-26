package com.stachura.praca_inz.backend.service.impl;

import com.stachura.praca_inz.backend.exception.EntityException;
import com.stachura.praca_inz.backend.model.Company;
import com.stachura.praca_inz.backend.repository.interfaces.CompanyRepository;
import com.stachura.praca_inz.backend.service.CompanyService;
import com.stachura.praca_inz.backend.web.dto.CompanyStructuresListElementDto;
import com.stachura.praca_inz.backend.web.dto.converter.CompanyStructureConverter;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.util.StackTraceUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

//TODO Obsługa zcustomizowanych wyjątków w każdym serwisie

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('COMPANY_READ')")
    public Company getCompanyById(Long id) {
        return companyRepository.find(id);
    }

    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('COMPANY_READ')")
    public Company getCompanyByName(String name) {
        return companyRepository.find(name);
    }

    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('COMPANY_LIST_READ')")
    public List<CompanyStructuresListElementDto> getAllCompanies() {
        List<Company> companies = companyRepository.findAll();
        List<CompanyStructuresListElementDto> companiesDto = new ArrayList<>();
        for (Company a : companies) {
            companiesDto.add(CompanyStructureConverter.toCompanyStructureListElement(a));
        }
        return companiesDto;
    }

    //TODO
    @Override
    @Transactional
    @PreAuthorize("hasAuthority('COMPANY_CREATE')")
    public void createNewCompany(Company company) {

        try {
            companyRepository.create(company);
        } catch (EntityException e) {
            e.printStackTrace();
        }


    }

    //TODO
    @Override
    @Transactional
    @PreAuthorize("hasAuthority('COMPANY_UPDATE')")
    public Company updateCompany(Company company) {
        Company tmp = new Company();

        try {
            tmp = companyRepository.update(company);
        } catch (EntityException e) {
            e.printStackTrace();
        }

        return tmp;
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('COMPANY_DELETE')")
    public void deleteCompanyById(Long id) {
        companyRepository.remove(id);
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('COMPANY_DELETE')")
    public void deleteCompany(Company company) {
        companyRepository.remove(company);
    }
}
