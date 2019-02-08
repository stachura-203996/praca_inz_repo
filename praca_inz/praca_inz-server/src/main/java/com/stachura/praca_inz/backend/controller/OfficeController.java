package com.stachura.praca_inz.backend.controller;

import com.stachura.praca_inz.backend.exception.EntityOptimisticLockException;
import com.stachura.praca_inz.backend.exception.base.SystemBaseException;
import com.stachura.praca_inz.backend.service.OfficeService;
import com.stachura.praca_inz.backend.web.dto.company.CompanyStructureAddDto;
import com.stachura.praca_inz.backend.web.dto.company.CompanyStructureEditDto;
import com.stachura.praca_inz.backend.web.dto.company.CompanyStructureViewDto;
import com.stachura.praca_inz.backend.web.dto.company.CompanyStructuresListElementDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.OptimisticLockException;
import java.util.List;

@RestController
@RequestMapping("/secured/structure/office")
@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = SystemBaseException.class)
public class OfficeController {

    @Autowired
    private OfficeService officeService;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    List<CompanyStructuresListElementDto> getAll() throws SystemBaseException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return officeService.getAll(auth.getName());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    CompanyStructureViewDto getToView(@PathVariable Long id) throws SystemBaseException {
        return officeService.getOfficeToView(id);
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    CompanyStructureEditDto get(@PathVariable Long id) throws SystemBaseException {
        return officeService.getOfficeToEdit(id);
    }


    @RequestMapping(value = "/company/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    List<CompanyStructuresListElementDto> getAllOfficesForCompany(@PathVariable Long id) {
        return officeService.getAllOfficesForCompany(id);
    }

    @RequestMapping(value = "/department/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    List<CompanyStructuresListElementDto> getAllOfficesForDepartment(@PathVariable Long id) {
        return officeService.getAllOfficesForDepartment(id);
    }

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<?> create(@RequestBody CompanyStructureAddDto companyStructureAddDto) throws SystemBaseException {
        try {
            officeService.create(companyStructureAddDto);
        } catch (OptimisticLockException e) {
            throw new EntityOptimisticLockException(EntityOptimisticLockException.OPTIMISTIC_LOCK);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public void update(@RequestBody CompanyStructureEditDto companyStructureEditDto) throws SystemBaseException {
        try {
            officeService.update(companyStructureEditDto);
        } catch (OptimisticLockException e) {
            throw new EntityOptimisticLockException(EntityOptimisticLockException.OPTIMISTIC_LOCK);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public void delete(@PathVariable Long id) throws SystemBaseException {
        officeService.delete(id);
    }
}
