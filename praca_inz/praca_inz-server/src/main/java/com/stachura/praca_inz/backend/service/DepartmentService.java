package com.stachura.praca_inz.backend.service;

import com.stachura.praca_inz.backend.exception.service.ServiceException;
import com.stachura.praca_inz.backend.model.Department;
import com.stachura.praca_inz.backend.web.dto.company.CompanyStructuresListElementDto;

import java.util.List;

public interface DepartmentService {
    Department getDepartmentById(Long id);

    Department getDepartmentByName(String name);

    List<CompanyStructuresListElementDto> getAllDepartments();

    List<CompanyStructuresListElementDto> getAllDepartmentsForCompany(Long id);

    void createNewDepartment(Department department) throws ServiceException;

    void updateDepartment(Department department) throws ServiceException;

    void deleteDepartmentById(Long id);

    void deleteDepartment(Department department);
}
