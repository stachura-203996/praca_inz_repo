package com.stachura.praca_inz.backend.web.dto.converter;

import com.stachura.praca_inz.backend.model.Address;
import com.stachura.praca_inz.backend.model.Company;
import com.stachura.praca_inz.backend.model.Department;
import com.stachura.praca_inz.backend.model.Office;
import com.stachura.praca_inz.backend.web.dto.company.CompanyStructureAddDto;
import com.stachura.praca_inz.backend.web.dto.company.CompanyStructureEditDto;
import com.stachura.praca_inz.backend.web.dto.company.CompanyStructureViewDto;
import com.stachura.praca_inz.backend.web.dto.company.CompanyStructuresListElementDto;

/**
 * Dostarcza metod do konwersji obiektów związanych ze strukturą firmy
 */
public class CompanyStructureConverter {


    //VIEW
    public static CompanyStructureViewDto toCompanyStructureViewDto(Company company) {
        return CompanyStructureViewDto.builder()
                .id(company.getId())
                .name(company.getName())
                .description(company.getDescription())
                .city(company.getAddress().getCity())
                .street(company.getAddress().getStreet())
                .buildingNumber(company.getAddress().getBuildingNumber())
                .flatNumber(company.getAddress().getFlatNumber())
                .build();
    }

    public static CompanyStructureViewDto toCompanyStructureViewDto(Department department) {
        return CompanyStructureViewDto.builder()
                .id(department.getId())
                .name(department.getName())
                .description(department.getDescription())
                .companyName(department.getCompany().getName())
                .build();
    }

    public static CompanyStructureViewDto toCompanyStructureViewDto(Office office) {
        return CompanyStructureViewDto.builder()
                .id(office.getId())
                .name(office.getName())
                .description(office.getDescription())
                .city(office.getAddress().getCity())
                .street(office.getAddress().getStreet())
                .buildingNumber(office.getAddress().getBuildingNumber())
                .flatNumber(office.getAddress().getFlatNumber())
                .companyName(office.getDepartment().getCompany().getName())
                .departmentName(office.getDepartment().getName())
                .build();
    }

    //LIST
    public static CompanyStructuresListElementDto toCompanyStructureListElement(Company company) {
        return CompanyStructuresListElementDto.builder()
                .id(company.getId())
                .name(company.getName())
                .description(company.getDescription())
                .city(company.getAddress().getCity())
                .street(company.getAddress().getStreet())
                .buildingNumber(company.getAddress().getBuildingNumber())
                .flatNumber(company.getAddress().getFlatNumber())
                .build();
    }

    public static CompanyStructuresListElementDto toCompanyStructureListElement(Department department) {

        return CompanyStructuresListElementDto.builder()
                .id(department.getId())
                .name(department.getName())
                .description(department.getDescription())
                .companyName(department.getCompany().getName())
                .build();
    }

    public static CompanyStructuresListElementDto toCompanyStructureListElement(Office office) {
        return CompanyStructuresListElementDto.builder()
                .id(office.getId())
                .name(office.getName())
                .description(office.getDescription())
                .city(office.getAddress().getCity())
                .street(office.getAddress().getStreet())
                .buildingNumber(office.getAddress().getBuildingNumber())
                .flatNumber(office.getAddress().getFlatNumber())
                .companyName(office.getDepartment().getCompany().getName())
                .departmentName(office.getDepartment().getName())
                .build();
    }

    //TO EDIT DTO
    public static CompanyStructureEditDto toCompanyStructureEdit(Company company) {
        return CompanyStructureEditDto.builder()
                .id(company.getId())
                .name(company.getName())
                .description(company.getDescription())
                .city(company.getAddress().getCity())
                .street(company.getAddress().getStreet())
                .buildingNumber(company.getAddress().getBuildingNumber())
                .flatNumber(company.getAddress().getFlatNumber())
                .verison(company.getVersion())
                .build();
    }

    public static CompanyStructureEditDto toCompanyStructureEdit(Department department) {
        return CompanyStructureEditDto.builder()
                .id(department.getId())
                .name(department.getName())
                .description(department.getDescription())
                .parentId(department.getCompany().getId())
                .parentName(department.getCompany().getName())
                .verison(department.getVersion())
                .build();
    }

    public static CompanyStructureEditDto toCompanyStructureEdit(Office office) {
        return CompanyStructureEditDto.builder()
                .id(office.getId())
                .name(office.getName())
                .description(office.getDescription())
                .city(office.getAddress().getCity())
                .street(office.getAddress().getStreet())
                .buildingNumber(office.getAddress().getBuildingNumber())
                .flatNumber(office.getAddress().getFlatNumber())
                .parentId(office.getDepartment().getId())
                .parentName(office.getDepartment().getName() + " | " + office.getDepartment().getCompany().getName())
                .verison(office.getVersion())
                .build();
    }


    //SAVE AFTER EDIT
    public static Department toDepartment(CompanyStructureEditDto companyStructureEditDto, Department beforeDepartment, Company company) {
        Department department =new Department(beforeDepartment.getId(),beforeDepartment.getVersion());
        department.setName(companyStructureEditDto.getName());
        department.setDescription(companyStructureEditDto.getDescription());
        department.setCompany(company);
        department.setVersion(companyStructureEditDto.getVerison());
        return department;
    }

    public static Company toCompany(CompanyStructureEditDto companyStructureEditDto, Company beforeCompany) {
        Company company=new Company(beforeCompany.getId(),beforeCompany.getVersion());
        company.setName(companyStructureEditDto.getName());
        company.setDescription(companyStructureEditDto.getDescription());
        company.getAddress().setCity(companyStructureEditDto.getCity());
        company.getAddress().setStreet(companyStructureEditDto.getStreet());
        company.getAddress().setBuildingNumber(companyStructureEditDto.getBuildingNumber());
        company.getAddress().setFlatNumber(companyStructureEditDto.getFlatNumber());
        company.setVersion(companyStructureEditDto.getVerison());
        return company;
    }

    public static Office toOffice(CompanyStructureEditDto companyStructureEditDto, Office beforeOffice, Department department) {
        Office office=new Office(beforeOffice.getId(),beforeOffice.getVersion());
        office.setName(companyStructureEditDto.getName());
        office.setDescription(companyStructureEditDto.getDescription());
        office.getAddress().setCity(companyStructureEditDto.getCity());
        office.getAddress().setStreet(companyStructureEditDto.getStreet());
        office.getAddress().setBuildingNumber(companyStructureEditDto.getBuildingNumber());
        office.getAddress().setFlatNumber(companyStructureEditDto.getFlatNumber());
        office.setVersion(companyStructureEditDto.getVerison());
        office.setDepartment(department);
        return office;
    }


    //ADD
    public static Company toCompany(CompanyStructureAddDto companyStructureAddDto) {
        Company company = new Company();
        company.setName(companyStructureAddDto.getName());
        company.setDescription(companyStructureAddDto.getDescription());
        Address adress = new Address();
        adress.setCity(companyStructureAddDto.getCity());
        adress.setStreet(companyStructureAddDto.getStreet());
        adress.setBuildingNumber(companyStructureAddDto.getBuildingNumber());
        adress.setFlatNumber(companyStructureAddDto.getFlatNumber());
        company.setAddress(adress);
        return company;
    }

    public static Department toDepartment(CompanyStructureAddDto companyStructureAddDto, Company company) {
        Department department = new Department();
        department.setName(companyStructureAddDto.getName());
        department.setDescription(companyStructureAddDto.getDescription());
        department.setCompany(company);
        return department;
    }

    public static Office toOffice(CompanyStructureAddDto companyStructureAddDto, Department department) {
        Office office = new Office();
        office.setDepartment(department);
        office.setName(companyStructureAddDto.getName());
        office.setDescription(companyStructureAddDto.getDescription());
        Address adress = new Address();
        adress.setCity(companyStructureAddDto.getCity());
        adress.setStreet(companyStructureAddDto.getStreet());
        adress.setBuildingNumber(companyStructureAddDto.getBuildingNumber());
        adress.setFlatNumber(companyStructureAddDto.getFlatNumber());
        office.setAddress(adress);
        return office;
    }
}
