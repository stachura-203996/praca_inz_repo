package com.stachura.praca_inz.backend.service;

import com.stachura.praca_inz.backend.model.Department;
import com.stachura.praca_inz.backend.web.dto.CompanyStructuresListElementDto;

import java.util.List;

public interface DepartmentService {
    Department getDepartmentById(Long id);

    Department getDepartmentByName(String name);

    List<CompanyStructuresListElementDto> getAllDepartments();

    void createNewDepartment(Department department);

    Department updateDepartment(Department department);

    void deleteDepartmentById(Long id);

    void deleteDepartment(Department department);
}
