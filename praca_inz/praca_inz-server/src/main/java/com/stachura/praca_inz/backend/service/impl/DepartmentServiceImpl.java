package com.stachura.praca_inz.backend.service.impl;

import com.stachura.praca_inz.backend.exception.EntityException;
import com.stachura.praca_inz.backend.model.Department;
import com.stachura.praca_inz.backend.model.Department;
import com.stachura.praca_inz.backend.repository.interfaces.DepartmentRepository;
import com.stachura.praca_inz.backend.service.DepartmentService;
import com.stachura.praca_inz.backend.web.dto.CompanyStructuresListElementDto;
import com.stachura.praca_inz.backend.web.dto.converter.CompanyStructureConverter;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    @Transactional(readOnly = true)
//    @PreAuthorize("hasAuthority('COMPANY_READ') and hasAuthority('DEPARTMENT_READ')")
    public Department getDepartmentById(Long id) {
        return departmentRepository.find(id);
    }

    @Override
    @Transactional(readOnly = true)
//    @PreAuthorize("hasAuthority('COMPANY_READ') and hasAuthority('DEPARTMENT_READ')")
    public Department getDepartmentByName(String name) {
        return departmentRepository.find(name);
    }

    @Override
    @Transactional(readOnly = true)
//    @PreAuthorize("hasAuthority('COMPANY_READ')")
    public List<CompanyStructuresListElementDto> getAllDepartments() {
        List<Department> departments = departmentRepository.findAll();
        List<CompanyStructuresListElementDto> structuresListElementDtos = new ArrayList<>();
        for (Department a : departments) {
            structuresListElementDtos.add(CompanyStructureConverter.toCompanyStructureListElement(a));
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
            structuresListElementDtos.add(CompanyStructureConverter.toCompanyStructureListElement(a));
        }
        return structuresListElementDtos;
    }

    //TODO EXCEPTIONS
    @Override
    @Transactional
//    @PreAuthorize("hasAuthority('COMPANY_CREATE')")
    public void createNewDepartment(Department department) {
        try {
            departmentRepository.create(department);
        } catch (EntityException e) {

        }
    }

    //TODO EXCEPTIONS
    @Override
    @Transactional
//    @PreAuthorize("hasAuthority('COMPANY_UPDATE')")
    public Department updateDepartment(Department department) {
        Department tmp = new Department();
        try {
            tmp = departmentRepository.update(department);
        } catch (EntityException e) {
            e.printStackTrace();
        }
        return tmp;
    }

    @Override
    @Transactional
//    @PreAuthorize("hasAuthority('COMPANY_DELETE')")
    public void deleteDepartmentById(Long id) {
        departmentRepository.remove(id);
    }

    @Override
    @Transactional
//    @PreAuthorize("hasAuthority('COMPANY_DELETE')")
    public void deleteDepartment(Department department) {
        departmentRepository.remove(department);
    }
}
