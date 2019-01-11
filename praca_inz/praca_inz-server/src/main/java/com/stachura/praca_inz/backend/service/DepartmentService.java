package com.stachura.praca_inz.backend.service;

import com.stachura.praca_inz.backend.exception.service.ServiceException;
import com.stachura.praca_inz.backend.model.Department;
import com.stachura.praca_inz.backend.web.dto.company.CompanyStructureAddDto;
import com.stachura.praca_inz.backend.web.dto.company.CompanyStructureEditDto;
import com.stachura.praca_inz.backend.web.dto.company.CompanyStructuresListElementDto;

import java.util.List;

public interface DepartmentService {
    Department getDepartmentById(Long id) throws ServiceException;

    List<CompanyStructuresListElementDto> getAllDepartments(String username) throws ServiceException;

    List<CompanyStructuresListElementDto> getAllDepartmentsForCompany(Long id);

    void createNewDepartment(CompanyStructureAddDto companyStructureAddDto) throws ServiceException;

    void updateDepartment(CompanyStructureEditDto companyStructureEditDto) throws ServiceException;

    void deleteDepartmentById(Long id) throws ServiceException;

}
