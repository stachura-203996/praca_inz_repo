package com.stachura.praca_inz.backend.service.impl;

import com.stachura.praca_inz.backend.Constants;
import com.stachura.praca_inz.backend.exception.repository.DatabaseErrorException;
import com.stachura.praca_inz.backend.exception.repository.EntityException;
import com.stachura.praca_inz.backend.exception.service.ServiceException;
import com.stachura.praca_inz.backend.model.Department;
import com.stachura.praca_inz.backend.model.security.User;
import com.stachura.praca_inz.backend.repository.interfaces.DepartmentRepository;
import com.stachura.praca_inz.backend.repository.interfaces.UserRepository;
import com.stachura.praca_inz.backend.service.DepartmentService;
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
    private UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('DEPARTMENT_READ')")
    public Department getDepartmentById(Long id) {
        Department department = departmentRepository.find(id);
        if (department.isDeleted()) {
            return null;
        }
        return department;
    }

    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('DEPARTMENT_READ')")
    public Department getDepartmentByName(String name) {
        Department department = departmentRepository.find(name);
        if (department.isDeleted()) {
            return null;
        }
        return department;
    }

    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('DEPARTMENT_LIST_READ')")
    public List<CompanyStructuresListElementDto> getAllDepartments(String username) {
        User user=userRepository.find(username);
        List<Department> departments;
        if(user.getUserRoles().stream().anyMatch(x->x.getName().equals(Constants.ADMIN_ROLE))) {
             departments = departmentRepository.findAll();
        } else{
          departments = departmentRepository.findAll().stream().filter(x->x.getCompany().getId().equals(user.getOffice().getDepartment().getCompany().getId())).collect(Collectors.toList());
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
        List<Department> departments = departmentRepository.findAll().stream().filter(x -> {
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
    public void createNewDepartment(Department department) throws ServiceException {
        try {
            departmentRepository.create(department);
        } catch (DatabaseErrorException e) {
            throw e;
        } catch (EntityException e) {
            throw ServiceException.createServiceException(ServiceException.ENTITY_VALIDATION, e);
        }
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('DEPARTMENT_UPDATE')")
    public void updateDepartment(Department department) throws ServiceException {

        departmentRepository.update(department);

    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('DEPARTMENT_DELETE')")
    public void deleteDepartmentById(Long id) {
        departmentRepository.find(id).setDeleted(true);

    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('DEPARTMENT_DELETE')")
    public void deleteDepartment(Department department) {
        departmentRepository.find(department.getId()).setDeleted(true);
    }
}
