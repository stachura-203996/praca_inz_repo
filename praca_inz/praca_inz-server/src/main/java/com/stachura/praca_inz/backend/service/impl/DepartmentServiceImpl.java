package com.stachura.praca_inz.backend.service.impl;

import com.google.common.collect.Lists;
import com.stachura.praca_inz.backend.Constants;
import com.stachura.praca_inz.backend.exception.repository.DatabaseErrorException;
import com.stachura.praca_inz.backend.exception.repository.EntityException;
import com.stachura.praca_inz.backend.exception.service.ServiceException;
import com.stachura.praca_inz.backend.model.Company;
import com.stachura.praca_inz.backend.model.Department;
import com.stachura.praca_inz.backend.model.security.User;
import com.stachura.praca_inz.backend.repository.CompanyRepository;
import com.stachura.praca_inz.backend.repository.DepartmentRepository;
import com.stachura.praca_inz.backend.repository.UserRepository;
import com.stachura.praca_inz.backend.service.DepartmentService;
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
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('DEPARTMENT_READ')")
    public Department getDepartmentById(Long id) throws ServiceException {
        Department department = departmentRepository.findById(id).orElseThrow(() -> new ServiceException());
        if (department.isDeleted()) {
            return null;
        }
        return department;
    }

    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('DEPARTMENT_LIST_READ')")
    public List<CompanyStructuresListElementDto> getAllDepartments(String username) throws ServiceException {
        User user=userRepository.findByUsername(username).orElseThrow(() -> new ServiceException());
        List<Department> departments;
        if(user.getUserRoles().stream().anyMatch(x->x.getName().equals(Constants.ADMIN_ROLE))) {
             departments = Lists.newArrayList(departmentRepository.findAll());
        } else{
          departments = Lists.newArrayList(departmentRepository.findAll()).stream().filter(x->x.getCompany().getId().equals(user.getOffice().getDepartment().getCompany().getId())).collect(Collectors.toList());
        }
        List<CompanyStructuresListElementDto> structuresListElementDtos = new ArrayList<>();
        for (Department a : departments) {
            if (!a.isDeleted()) {
                structuresListElementDtos.add(CompanyStructureConverter.toCompanyStructureListElement(a));
            }
        }
        return structuresListElementDtos;

    }

    @Override
    public List<CompanyStructuresListElementDto> getAllDepartmentsForCompany(Long id) {
        List<Department> departments = Lists.newArrayList(departmentRepository.findAll()).stream().filter(x -> {
            Hibernate.initialize(x.getCompany());
            return x.getCompany().getId().equals(id);
        }).collect(Collectors.toList());
        List<CompanyStructuresListElementDto> structuresListElementDtos = new ArrayList<>();
        for (Department a : departments) {
            if (!a.isDeleted()) {
                structuresListElementDtos.add(CompanyStructureConverter.toCompanyStructureListElement(a));
            }
        }
        return structuresListElementDtos;
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('DEPARTMENT_CREATE')")
    public void createNewDepartment(CompanyStructureAddDto companyStructureAddDto) throws ServiceException {
        try {
            Company company=companyRepository.findById(companyStructureAddDto.getCompanyId()).orElseThrow(() -> new ServiceException());
            departmentRepository.save(CompanyStructureConverter.toDepartment(companyStructureAddDto,company));
        } catch (DatabaseErrorException e) {
            throw e;
        } catch (EntityException e) {
            throw ServiceException.createServiceException(ServiceException.ENTITY_VALIDATION, e);
        }
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('DEPARTMENT_UPDATE')")
    public void updateDepartment(CompanyStructureEditDto companyStructureEditDto) throws ServiceException {
        Department beforeDepartment=departmentRepository.findById(companyStructureEditDto.getId()).orElseThrow(() -> new ServiceException());
        Company company=companyRepository.findById(companyStructureEditDto.getParentId()).orElseThrow(() -> new ServiceException());
        departmentRepository.save( CompanyStructureConverter.toDepartment(companyStructureEditDto,beforeDepartment,company));
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('DEPARTMENT_DELETE')")
    public void deleteDepartmentById(Long id) throws ServiceException {
        departmentRepository.findById(id).orElseThrow(() -> new ServiceException()).setDeleted(true);

    }

}
