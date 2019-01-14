package com.stachura.praca_inz.backend.service;

import com.stachura.praca_inz.backend.exception.base.AppBaseException;
import com.stachura.praca_inz.backend.model.Department;
import com.stachura.praca_inz.backend.web.dto.company.CompanyStructureAddDto;
import com.stachura.praca_inz.backend.web.dto.company.CompanyStructureEditDto;
import com.stachura.praca_inz.backend.web.dto.company.CompanyStructuresListElementDto;

import java.util.List;

public interface DepartmentService {
    Department getDepartmentById(Long id) throws AppBaseException;

    List<CompanyStructuresListElementDto> getAllDepartments(String username) throws AppBaseException;

    List<CompanyStructuresListElementDto> getAllDepartmentsForCompany(Long id);

    void createNewDepartment(CompanyStructureAddDto companyStructureAddDto) throws AppBaseException;

    void updateDepartment(CompanyStructureEditDto companyStructureEditDto) throws AppBaseException;

    void deleteDepartmentById(Long id) throws AppBaseException;

}
