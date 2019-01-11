package com.stachura.praca_inz.backend.service.impl;

import com.google.common.collect.Lists;
import com.stachura.praca_inz.backend.exception.repository.DatabaseErrorException;
import com.stachura.praca_inz.backend.exception.repository.EntityException;
import com.stachura.praca_inz.backend.exception.service.ServiceException;
import com.stachura.praca_inz.backend.model.Department;
import com.stachura.praca_inz.backend.model.Office;
import com.stachura.praca_inz.backend.repository.DepartmentRepository;
import com.stachura.praca_inz.backend.repository.OfficeRepository;
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
    public Office getOfficeById(Long id) throws ServiceException {
        Office office = officeRepository.findById(id).orElseThrow(() -> new ServiceException());
        if (office.isDeleted()) {
            return null;
        }
        return office;
    }

    @Override
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
    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('OFFICE_LIST_READ')")
    public List<CompanyStructuresListElementDto> getAll() {
        List<Office> offices = Lists.newArrayList(officeRepository.findAll());
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
        Department department=departmentRepository.findById(companyStructureAddDto.getDepartmentId()).orElseThrow(() -> new ServiceException());
        officeRepository.save(CompanyStructureConverter.toOffice(companyStructureAddDto,department));

    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('OFFICE_UPDATE')")
    public void update(CompanyStructureEditDto companyStructureEditDto) throws ServiceException {
        Office beforeOffice=officeRepository.findById(companyStructureEditDto.getId()).orElseThrow(() -> new ServiceException());
        Department department=departmentRepository.findById(companyStructureEditDto.getParentId()).orElseThrow(() -> new ServiceException());
        officeRepository.save(CompanyStructureConverter.toOffice(companyStructureEditDto,beforeOffice,department));
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('OFFICE_DELETE')")
    public void delete(Long id) throws ServiceException {
        officeRepository.findById(id).orElseThrow(() -> new ServiceException()).setDeleted(true);
    }
}
