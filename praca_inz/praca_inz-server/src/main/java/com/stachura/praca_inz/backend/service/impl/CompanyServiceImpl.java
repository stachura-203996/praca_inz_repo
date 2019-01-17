package com.stachura.praca_inz.backend.service.impl;

import com.stachura.praca_inz.backend.exception.DatabaseErrorException;
import com.stachura.praca_inz.backend.exception.EntityNotInDatabaseException;
import com.stachura.praca_inz.backend.exception.EntityOptimisticLockException;
import com.stachura.praca_inz.backend.model.Company;
import com.stachura.praca_inz.backend.repository.AddressRepository;
import com.stachura.praca_inz.backend.repository.CompanyRepository;
import com.stachura.praca_inz.backend.service.CompanyService;
import com.stachura.praca_inz.backend.web.dto.company.CompanyStructureAddDto;
import com.stachura.praca_inz.backend.web.dto.company.CompanyStructureEditDto;
import com.stachura.praca_inz.backend.web.dto.company.CompanyStructureViewDto;
import com.stachura.praca_inz.backend.web.dto.company.CompanyStructuresListElementDto;
import com.stachura.praca_inz.backend.web.dto.converter.CompanyStructureConverter;
import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import java.util.ArrayList;
import java.util.List;


@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    AddressRepository addressRepository;

    @PersistenceContext
    EntityManager em;

    final static Logger logger = Logger.getLogger(CompanyService.class);



    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    @PreAuthorize("hasAuthority('COMPANY_CREATE')")
    public void createNewCompany(CompanyStructureAddDto companyStructureAddDto) throws DatabaseErrorException {
        try {
            companyRepository.saveAndFlush(CompanyStructureConverter.toCompany(companyStructureAddDto));
            em.flush();
        }catch (PersistenceException e){
            throw new DatabaseErrorException(DatabaseErrorException.COMPANY_NAME_TAKEN);
        }
    }


    @Override
    @Transactional(readOnly = true,propagation = Propagation.MANDATORY)
    @PreAuthorize("hasAuthority('COMPANY_VIEW_READ')")
    public CompanyStructureViewDto getCompanyToView(Long id) throws EntityNotInDatabaseException {
        Company company = companyRepository.findById(id).orElseThrow(() -> new EntityNotInDatabaseException(EntityNotInDatabaseException.NO_OBJECT));
        if (company.isDeleted()) {
            return null;
        }
        return CompanyStructureConverter.toCompanyStructureViewDto(company);
    }

    @Override
    @Transactional(readOnly = true,propagation = Propagation.MANDATORY)
    @PreAuthorize("hasAuthority('COMPANY_EDIT_READ')")
    public CompanyStructureEditDto getCompanyToEdit(Long id) throws EntityNotInDatabaseException {
        Company company = companyRepository.findById(id).orElseThrow(() -> new EntityNotInDatabaseException(EntityNotInDatabaseException.NO_OBJECT));
        if (company.isDeleted()) {
            return null;
        }
        return CompanyStructureConverter.toCompanyStructureEdit(company);
    }

    @Override
    @Transactional(readOnly = true,propagation = Propagation.MANDATORY)
    @PreAuthorize("hasAuthority('COMPANY_LIST_READ')")
    public List<CompanyStructuresListElementDto> getAllCompanies() {
        List<Company> companies = (List<Company>) companyRepository.findAll();
        List<CompanyStructuresListElementDto> companiesDto = new ArrayList<>();
        for (Company a : companies) {
            if (!a.isDeleted()) {
                companiesDto.add(CompanyStructureConverter.toCompanyStructureListElement(a));
            }
        }
        return companiesDto;
    }



    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    @PreAuthorize("hasAuthority('COMPANY_UPDATE')")
    public void updateCompany(CompanyStructureEditDto companyStructureEditDto) throws EntityOptimisticLockException, EntityNotInDatabaseException, DatabaseErrorException {
        Company beforeCompany = companyRepository.findById(companyStructureEditDto.getId()).orElseThrow(() -> new EntityNotInDatabaseException(EntityNotInDatabaseException.NO_OBJECT));
        Hibernate.initialize(beforeCompany.getAddress());
        try {
            companyRepository.detachCompany(beforeCompany);
            companyRepository.save(CompanyStructureConverter.toCompany(companyStructureEditDto, beforeCompany));
        } catch (ObjectOptimisticLockingFailureException e) {
            throw new EntityOptimisticLockException(EntityOptimisticLockException.OPTIMISTIC_LOCK);
        } catch (PersistenceException e) {
            throw new DatabaseErrorException(DatabaseErrorException.COMPANY_NAME_TAKEN);
        }
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    @PreAuthorize("hasAuthority('COMPANY_DELETE')")
    public void deleteCompanyById(Long id) throws EntityNotInDatabaseException {
        companyRepository.findById(id).orElseThrow(() -> new EntityNotInDatabaseException(EntityNotInDatabaseException.NO_OBJECT)).setDeleted(true);
    }

}
