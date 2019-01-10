package com.stachura.praca_inz.backend.service.impl;

import com.stachura.praca_inz.backend.exception.repository.DatabaseErrorException;
import com.stachura.praca_inz.backend.exception.repository.EntityException;
import com.stachura.praca_inz.backend.exception.service.ServiceException;
import com.stachura.praca_inz.backend.model.Address;
import com.stachura.praca_inz.backend.model.Company;
import com.stachura.praca_inz.backend.repository.interfaces.AddressRepository;
import com.stachura.praca_inz.backend.repository.interfaces.CompanyRepository;
import com.stachura.praca_inz.backend.service.CompanyService;
import com.stachura.praca_inz.backend.web.dto.company.CompanyStructureAddDto;
import com.stachura.praca_inz.backend.web.dto.company.CompanyStructureEditDto;
import com.stachura.praca_inz.backend.web.dto.company.CompanyStructuresListElementDto;
import com.stachura.praca_inz.backend.web.dto.converter.CompanyStructureConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    AddressRepository addressRepository;

    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('COMPANY_READ')")
    public Company getCompanyById(Long id) {
        Company company = companyRepository.find(id);
        if (company.isDeleted()) {
            return null;
        }
        return company;
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
            if (!a.isDeleted()) {
                companiesDto.add(CompanyStructureConverter.toCompanyStructureListElement(a));
            }
        }
        return companiesDto;
    }


    @Override
    @Transactional
    @PreAuthorize("hasAuthority('COMPANY_CREATE')")
    public Long createNewCompany(CompanyStructureAddDto companyStructureAddDto) throws ServiceException {

        Address address = new Address();
        address.setCity(companyStructureAddDto.getCity());
        address.setStreet(companyStructureAddDto.getStreet());
        address.setBuildingNumber(companyStructureAddDto.getBuildingNumber());
        address.setFlatNumber(companyStructureAddDto.getFlatNumber());
        address.setDeleted(false);
        Company company = new Company();
        company.setDeleted(false);
        company.setName(companyStructureAddDto.getName());
        company.setDescription(companyStructureAddDto.getDescription());

        try {
            addressRepository.create(address);
            company.setAddress(address);
            companyRepository.create(company);
            return company.getId();
        } catch (DatabaseErrorException e) {
            throw e;
        } catch (EntityException e) {
            throw ServiceException.createServiceException(ServiceException.ENTITY_VALIDATION, e);
        }
    }


    @Override
    @Transactional
    @PreAuthorize("hasAuthority('COMPANY_UPDATE')")
    public void updateCompany(CompanyStructureEditDto companyStructureEditDto) throws ServiceException {
        Company beforeCompany=companyRepository.find(companyStructureEditDto.getId());
        companyRepository.update(CompanyStructureConverter.toCompany(companyStructureEditDto,beforeCompany));
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('COMPANY_DELETE')")
    public void deleteCompanyById(Long id) {
        companyRepository.find(id).setDeleted(true);
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('COMPANY_DELETE')")
    public void deleteCompany(Company company) {
        companyRepository.find(company.getId()).setDeleted(true);
    }
}
