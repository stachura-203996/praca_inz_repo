package com.stachura.praca_inz.backend.service;

import com.stachura.praca_inz.backend.exception.service.ServiceException;
import com.stachura.praca_inz.backend.model.Office;
import com.stachura.praca_inz.backend.web.dto.company.CompanyStructuresListElementDto;

import java.util.List;

public interface OfficeService {
    Office getOfficeById(Long id);

//    Office getCompanyById(String name);

    List<CompanyStructuresListElementDto> getAllOfficesForCompany(Long id);

    List<CompanyStructuresListElementDto> getAllOfficesForDepartment(Long id);

    List<CompanyStructuresListElementDto> getAll();

    void create(Office office) throws ServiceException;

    void update(Office office) throws ServiceException;

    void delete(Long id);

    void delete(Office office);
}
