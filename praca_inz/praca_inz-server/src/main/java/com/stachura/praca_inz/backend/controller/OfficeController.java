package com.stachura.praca_inz.backend.controller;

import com.stachura.praca_inz.backend.exception.service.ServiceException;
import com.stachura.praca_inz.backend.model.Office;
import com.stachura.praca_inz.backend.repository.interfaces.DepartmentRepository;
import com.stachura.praca_inz.backend.service.OfficeService;
import com.stachura.praca_inz.backend.web.dto.company.CompanyStructureAddDto;
import com.stachura.praca_inz.backend.web.dto.company.CompanyStructureEditDto;
import com.stachura.praca_inz.backend.web.dto.company.CompanyStructureViewDto;
import com.stachura.praca_inz.backend.web.dto.company.CompanyStructuresListElementDto;
import com.stachura.praca_inz.backend.web.dto.converter.CompanyStructureConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@RestController
@RequestMapping("/secured/structure/office")
public class OfficeController {

    @Autowired
    private OfficeService officeService;

    @Autowired
    private DepartmentRepository departmentRepository;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    List<CompanyStructuresListElementDto> getAll() {
        return officeService.getAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    CompanyStructureViewDto getToView(@PathVariable Long id) {
        return CompanyStructureConverter.toCompanyStructureViewDto(officeService.getOfficeById(id));
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    CompanyStructureEditDto get(@PathVariable Long id) {
        return CompanyStructureConverter.toCompanyStructureEdit(officeService.getOfficeById(id));
    }


    @RequestMapping(value = "/company/{id}",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    List<CompanyStructuresListElementDto> getAllOfficesForCompany(@PathVariable Long id) {
        return officeService.getAllOfficesForCompany(id);
    }

    @RequestMapping(value = "/department/{id}",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    List<CompanyStructuresListElementDto> getAllOfficesForDepartment(@PathVariable Long id) {
        return officeService.getAllOfficesForDepartment(id);
    }

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<?> create(@RequestBody CompanyStructureAddDto companyStructureAddDto) {
        try {
            officeService.create(companyStructureAddDto);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public void update(@RequestBody CompanyStructureEditDto companyStructureEditDto) {
        Office beforeOffice=officeService.getOfficeById(companyStructureEditDto.getId());
        try {
            officeService.update(companyStructureEditDto);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        officeService.delete(id);
    }
}
