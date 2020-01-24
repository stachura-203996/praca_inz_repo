package com.stachura.praca_inz.backend.service;

import com.stachura.praca_inz.backend.exception.EntityNotInDatabaseException;
import com.stachura.praca_inz.backend.exception.base.SystemBaseException;
import com.stachura.praca_inz.backend.model.Department;
import com.stachura.praca_inz.backend.web.dto.company.CompanyStructureAddDto;
import com.stachura.praca_inz.backend.web.dto.company.CompanyStructureEditDto;
import com.stachura.praca_inz.backend.web.dto.company.CompanyStructureViewDto;
import com.stachura.praca_inz.backend.web.dto.company.CompanyStructuresListElementDto;

import java.util.List;

public interface DepartmentService {
    Department getDepartmentById(Long id) throws SystemBaseException;

    List<CompanyStructuresListElementDto> getAllDepartments(String username) throws SystemBaseException;

    List<CompanyStructuresListElementDto> getAllDepartmentsForCompany(Long id);

    void createNewDepartment(CompanyStructureAddDto companyStructureAddDto) throws SystemBaseException;

    void updateDepartment(CompanyStructureEditDto companyStructureEditDto) throws SystemBaseException;

    void deleteDepartmentById(Long id) throws SystemBaseException;

    CompanyStructureViewDto getDepartmentView(Long id) throws EntityNotInDatabaseException;

    CompanyStructureEditDto getDepartmentEdit(Long id) throws EntityNotInDatabaseException;
}
