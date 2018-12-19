package com.stachura.praca_inz.backend.service.impl;

import com.stachura.praca_inz.backend.exception.EntityException;
import com.stachura.praca_inz.backend.model.Department;
import com.stachura.praca_inz.backend.repository.interfaces.DepartmentRepository;
import com.stachura.praca_inz.backend.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    @Transactional(readOnly = true)
//    @PreAuthorize("hasAuthority('COMPANY_READ') and hasAuthority('DEPARTMENT_READ')")
    public Department get(Long id) {
        return departmentRepository.find(id);
    }

    @Override
    @Transactional(readOnly = true)
//    @PreAuthorize("hasAuthority('COMPANY_READ') and hasAuthority('DEPARTMENT_READ')")
    public Department get(String name) {
        return departmentRepository.find(name);
    }

    @Override
    @Transactional(readOnly = true)
//    @PreAuthorize("hasAuthority('COMPANY_READ')")
    public List<Department> getAll() {
        return departmentRepository.findAll();
    }

    //TODO EXCEPTIONS
    @Override
    @Transactional
//    @PreAuthorize("hasAuthority('COMPANY_CREATE')")
    public void create(Department department) {
        try {
            departmentRepository.create(department);
        } catch (EntityException e){

        }
    }
//TODO EXCEPTIONS
    @Override
    @Transactional
//    @PreAuthorize("hasAuthority('COMPANY_UPDATE')")
    public Department update(Department department) {
        Department departmentR=new Department();
        try {
            departmentR= departmentRepository.update(department);
        } catch (EntityException e) {
            e.printStackTrace();
        }
        return departmentR;
    }

    @Override
    @Transactional
//    @PreAuthorize("hasAuthority('COMPANY_DELETE')")
    public void delete(Long id) {
        departmentRepository.remove(id);
    }

    @Override
    @Transactional
//    @PreAuthorize("hasAuthority('COMPANY_DELETE')")
    public void delete(Department department) {
        departmentRepository.remove(department);
    }
}
