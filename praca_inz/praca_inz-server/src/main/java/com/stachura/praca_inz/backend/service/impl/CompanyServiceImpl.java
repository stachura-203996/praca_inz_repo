package com.stachura.praca_inz.backend.service.impl;

import com.stachura.praca_inz.backend.exception.EntityException;
import com.stachura.praca_inz.backend.model.Company;
import com.stachura.praca_inz.backend.repository.interfaces.CompanyRepository;
import com.stachura.praca_inz.backend.service.CompanyService;
import com.stachura.praca_inz.backend.web.dto.CompanyStructuresListElementDto;
import com.stachura.praca_inz.backend.web.dto.converter.CompanyStructureConverter;
import org.springframework.beans.factory.annotation.Autowired;
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
    @PreAuthorize("hasAuthority('COMPANY_READ') and hasAuthority('DEPARTMENT_READ')")
    public Company get(Long id) {
        return companyRepository.find(id);
    }

    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('COMPANY_READ') and hasAuthority('DEPARTMENT_READ')")
    public Company get(String name) {
        return companyRepository.find(name);
    }

    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('COMPANY_READ')")
    public List<CompanyStructuresListElementDto> getAll() {
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
    public void create(Company company) {
        try {
            companyRepository.create(company);

        } catch (EntityException e) {

        }

    }

    //TODO
    @Override
    @Transactional
    @PreAuthorize("hasAuthority('COMPANY_UPDATE')")
    public Company update(Company company) {
        Company companyR = new Company();
        try {
            company = companyRepository.update(company);
        } catch (EntityException e) {

        }
        return companyR;
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('COMPANY_DELETE')")
    public void delete(Long id) {
        companyRepository.remove(id);
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('COMPANY_DELETE')")
    public void delete(Company company) {
        companyRepository.remove(company);
    }
}
