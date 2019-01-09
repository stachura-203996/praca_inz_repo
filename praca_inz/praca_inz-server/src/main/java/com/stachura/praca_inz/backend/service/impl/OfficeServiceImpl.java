package com.stachura.praca_inz.backend.service.impl;

import com.stachura.praca_inz.backend.exception.repository.DatabaseErrorException;
import com.stachura.praca_inz.backend.exception.repository.EntityException;
import com.stachura.praca_inz.backend.exception.service.ServiceException;
import com.stachura.praca_inz.backend.model.Department;
import com.stachura.praca_inz.backend.model.Office;
import com.stachura.praca_inz.backend.repository.interfaces.DepartmentRepository;
import com.stachura.praca_inz.backend.repository.interfaces.DeviceRepository;
import com.stachura.praca_inz.backend.repository.interfaces.OfficeRepository;
import com.stachura.praca_inz.backend.service.OfficeService;
import com.stachura.praca_inz.backend.web.dto.company.CompanyStructureAddDto;
import com.stachura.praca_inz.backend.web.dto.company.CompanyStructureEditDto;
import com.stachura.praca_inz.backend.web.dto.company.CompanyStructuresListElementDto;
import com.stachura.praca_inz.backend.web.dto.converter.CompanyStructureConverter;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OfficeServiceImpl implements OfficeService {

    @Autowired
    private OfficeRepository officeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('OFFICE_READ')")
    public Office getOfficeById(Long id) {
        Office office = officeRepository.find(id);
        if (office.isDeleted()) {
            return null;
        }
        return office;
    }

    @Override
    public List<CompanyStructuresListElementDto> getAllOfficesForCompany(Long id) {
        List<Office> offices = officeRepository.findAll().stream().filter(x -> x.getDepartment().getCompany().getId().equals(id)).collect(Collectors.toList());
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
    public List<CompanyStructuresListElementDto> getAllOfficesForDepartment(Long id) {
        List<Office> offices = officeRepository.findAll().stream().filter(x -> x.getDepartment().getId().equals(id)).collect(Collectors.toList());
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
    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('OFFICE_LIST_READ')")
    public List<CompanyStructuresListElementDto> getAll() {
        List<Office> offices = officeRepository.findAll();
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
    @Transactional
    @PreAuthorize("hasAuthority('OFFICE_CREATE')")
    public void create(CompanyStructureAddDto companyStructureAddDto)throws ServiceException {
        Department department=departmentRepository.find(companyStructureAddDto.getDepartmentId());
        try {
            officeRepository.create(CompanyStructureConverter.toOffice(companyStructureAddDto,department));
        } catch (DatabaseErrorException e) {
            throw e;
        } catch (EntityException e) {
            throw ServiceException.createServiceException(ServiceException.ENTITY_VALIDATION, e);
        }
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('OFFICE_UPDATE')")
    public void update(CompanyStructureEditDto companyStructureEditDto) throws ServiceException {
        Office beforeOffice=officeRepository.find(companyStructureEditDto.getId());
        Department department=departmentRepository.find(companyStructureEditDto.getParentId());
        officeRepository.update(CompanyStructureConverter.toOffice(companyStructureEditDto,beforeOffice,department));
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('OFFICE_DELETE')")
    public void delete(Long id) {
        officeRepository.find(id).setDeleted(true);
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('OFFICE_DELETE')")
    public void delete(Office office) {
        officeRepository.find(office.getId()).setDeleted(true);
    }
}
