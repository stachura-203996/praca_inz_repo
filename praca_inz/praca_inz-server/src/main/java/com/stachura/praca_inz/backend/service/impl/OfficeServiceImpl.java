package com.stachura.praca_inz.backend.service.impl;

import com.google.common.collect.Lists;
import com.stachura.praca_inz.backend.Constants;
import com.stachura.praca_inz.backend.exception.DatabaseErrorException;
import com.stachura.praca_inz.backend.exception.EntityNotInDatabaseException;
import com.stachura.praca_inz.backend.exception.EntityOptimisticLockException;
import com.stachura.praca_inz.backend.model.Department;
import com.stachura.praca_inz.backend.model.Office;
import com.stachura.praca_inz.backend.model.security.User;
import com.stachura.praca_inz.backend.repository.DepartmentRepository;
import com.stachura.praca_inz.backend.repository.OfficeRepository;
import com.stachura.praca_inz.backend.repository.UserRepository;
import com.stachura.praca_inz.backend.service.OfficeService;
import com.stachura.praca_inz.backend.web.dto.company.CompanyStructureAddDto;
import com.stachura.praca_inz.backend.web.dto.company.CompanyStructureEditDto;
import com.stachura.praca_inz.backend.web.dto.company.CompanyStructureViewDto;
import com.stachura.praca_inz.backend.web.dto.company.CompanyStructuresListElementDto;
import com.stachura.praca_inz.backend.web.dto.converter.CompanyStructureConverter;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OfficeServiceImpl implements OfficeService {

    @Autowired
    private OfficeRepository officeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EntityManager em;

    @Override
    @Transactional(readOnly = true, propagation = Propagation.MANDATORY)
    @PreAuthorize("hasAuthority('OFFICE_VIEW_READ')")
    public CompanyStructureViewDto getOfficeToView(Long id) throws EntityNotInDatabaseException {
        Office office = officeRepository.findById(id).orElseThrow(() -> new EntityNotInDatabaseException(EntityNotInDatabaseException.NO_OBJECT));
        if (office.isDeleted()) {
            return null;
        }
        return CompanyStructureConverter.toCompanyStructureViewDto(office);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.MANDATORY)
    @PreAuthorize("hasAuthority('OFFICE_EDIT_READ')")
    public CompanyStructureEditDto getOfficeToEdit(Long id) throws EntityNotInDatabaseException {
        Office office = officeRepository.findById(id).orElseThrow(() -> new EntityNotInDatabaseException(EntityNotInDatabaseException.NO_OBJECT));
        if (office.isDeleted()) {
            return null;
        }
        return CompanyStructureConverter.toCompanyStructureEdit(office);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.MANDATORY)
    @PreAuthorize("hasAuthority('OFFICE_LIST_FOR_COMPANY_READ')")
    public List<CompanyStructuresListElementDto> getAllOfficesForCompany(Long id) {
        List<Office> offices = Lists.newArrayList(officeRepository.findAll()).stream().filter(x -> x.getDepartment().getCompany().getId().equals(id)).collect(Collectors.toList());
        List<CompanyStructuresListElementDto> officesDto = new ArrayList<>();
        for (Office a : offices) {
            if (!a.isDeleted()) {
                Hibernate.initialize(a.getDepartment());
                Hibernate.initialize(a.getDepartment().getCompany());
                officesDto.add(CompanyStructureConverter.toCompanyStructureListElement(a));
            }
        }
        return officesDto;
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.MANDATORY)
    @PreAuthorize("hasAuthority('OFFICE_LIST_FOR_DEPARTMENT_READ')")
    public List<CompanyStructuresListElementDto> getAllOfficesForDepartment(Long id) {
        List<Office> offices = Lists.newArrayList(officeRepository.findAll()).stream().filter(x -> x.getDepartment().getId().equals(id)).collect(Collectors.toList());
        List<CompanyStructuresListElementDto> officesDto = new ArrayList<>();
        for (Office a : offices) {
            if (!a.isDeleted()) {
                Hibernate.initialize(a.getDepartment());
                Hibernate.initialize(a.getDepartment().getCompany());
                officesDto.add(CompanyStructureConverter.toCompanyStructureListElement(a));
            }
        }
        return officesDto;
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.MANDATORY)
    @PreAuthorize("hasAuthority('OFFICE_LIST_READ')")
    public List<CompanyStructuresListElementDto> getAll(String username) throws EntityNotInDatabaseException {
        List<Office> offices;
        User user = userRepository.findByUsername(username).orElseThrow(() -> new EntityNotInDatabaseException(EntityNotInDatabaseException.NO_OBJECT));
        if (user.getUserRoles().stream().anyMatch(x -> x.getName().equals(Constants.ADMIN_ROLE))) {
            offices = Lists.newArrayList(officeRepository.findAll());
        } else {
            offices = Lists.newArrayList(officeRepository.findAll()).stream().filter(x -> x.getDepartment().getCompany().getId().equals(user.getOffice().getDepartment().getCompany().getId())).collect(Collectors.toList());
        }
        List<CompanyStructuresListElementDto> officesDto = new ArrayList<>();
        for (Office a : offices) {
            if (!a.isDeleted()) {
                Hibernate.initialize(a.getDepartment());
                Hibernate.initialize(a.getDepartment().getCompany());
                officesDto.add(CompanyStructureConverter.toCompanyStructureListElement(a));
            }
        }
        return officesDto;
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    @PreAuthorize("hasAuthority('OFFICE_CREATE')")
    public void create(CompanyStructureAddDto companyStructureAddDto) throws EntityNotInDatabaseException, DatabaseErrorException {
        try {
            Department department = departmentRepository.findById(companyStructureAddDto.getDepartmentId()).orElseThrow(() -> new EntityNotInDatabaseException(EntityNotInDatabaseException.NO_OBJECT));
            officeRepository.save(CompanyStructureConverter.toOffice(companyStructureAddDto, department));
        } catch (PersistenceException e) {
            throw new DatabaseErrorException(DatabaseErrorException.DEPARTMENT_NAME_TAKEN);
        }
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    @PreAuthorize("hasAuthority('OFFICE_UPDATE')")
    public void update(CompanyStructureEditDto companyStructureEditDto) throws EntityNotInDatabaseException, EntityOptimisticLockException, DatabaseErrorException {
        try {
            Office beforeOffice = officeRepository.findById(companyStructureEditDto.getId()).orElseThrow(()->new EntityNotInDatabaseException(EntityNotInDatabaseException.NO_OBJECT));
            Department department = departmentRepository.findById(companyStructureEditDto.getParentId()).orElseThrow(() -> new EntityNotInDatabaseException(EntityNotInDatabaseException.NO_OBJECT));
            Hibernate.initialize(beforeOffice.getAddress());
            officeRepository.detach(beforeOffice);
            officeRepository.save(CompanyStructureConverter.toOffice(companyStructureEditDto, beforeOffice, department));
        } catch (ObjectOptimisticLockingFailureException e) {
            throw new EntityOptimisticLockException(EntityOptimisticLockException.OPTIMISTIC_LOCK);
        } catch (PersistenceException e) {
            throw new DatabaseErrorException(DatabaseErrorException.DEPARTMENT_NAME_TAKEN);
        }
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    @PreAuthorize("hasAuthority('OFFICE_DELETE')")
    public void delete(Long id) throws EntityNotInDatabaseException {
        officeRepository.findById(id).orElseThrow(() -> new EntityNotInDatabaseException(EntityNotInDatabaseException.NO_OBJECT)).setDeleted(true);
    }

}
