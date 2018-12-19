package com.stachura.praca_inz.backend.service.impl;

import com.stachura.praca_inz.backend.model.Office;
import com.stachura.praca_inz.backend.repository.interfaces.OfficeRepository;
import com.stachura.praca_inz.backend.service.OfficeService;
import com.stachura.praca_inz.backend.web.dto.CompanyStructuresListElementDto;
import com.stachura.praca_inz.backend.web.dto.converter.CompanyStructureConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class OfficeServiceImpl implements OfficeService {

    @Autowired
    private OfficeRepository officeRepository;

    @Override
    @Transactional(readOnly = true)
//    @PreAuthorize("hasAuthority('COMPANY_READ') and hasAuthority('DEPARTMENT_READ')")
    public Office get(Long id) {
        return officeRepository.find(id);
    }

    @Override
    @Transactional(readOnly = true)
//    @PreAuthorize("hasAuthority('COMPANY_READ') and hasAuthority('DEPARTMENT_READ')")
    public Office get(String name) {
        return officeRepository.find(name);
    }

    @Override
    @Transactional(readOnly = true)
//    @PreAuthorize("hasAuthority('COMPANY_READ')")
    public List<CompanyStructuresListElementDto> getAll() {
        List<Office> companies = officeRepository.findAll();
        List<CompanyStructuresListElementDto> companiesDto = new ArrayList<>();
        for (Office a : companies) {
            companiesDto.add(CompanyStructureConverter.toCompanyStructureListElement(a));
        }
        return companiesDto;
    }

    @Override
    @Transactional
//    @PreAuthorize("hasAuthority('COMPANY_CREATE')")
    public void create(Office office) {
        officeRepository.create(office);
    }

    @Override
    @Transactional
//    @PreAuthorize("hasAuthority('COMPANY_UPDATE')")
    public Office update(Office office) {
        return officeRepository.update(office);
    }

    @Override
    @Transactional
//    @PreAuthorize("hasAuthority('COMPANY_DELETE')")
    public void delete(Long id) {
        officeRepository.delete(id);
    }

    @Override
    @Transactional
//    @PreAuthorize("hasAuthority('COMPANY_DELETE')")
    public void delete(Office office) {
        officeRepository.delete(office);
    }
}
