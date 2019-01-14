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

    /**
     * Metoda konwertująca encję {@link Company} na obiekt {@link CompanyStructuresListElementDto} przesyłany do widoku
     *
     * @param company encja firmy
     * @return obiekt z informacjami o firmie
     */
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

    /**
     * Metoda konwertująca encję {@link Department} na obiekt {@link CompanyStructuresListElementDto} przesyłany do widoku
     *
     * @param department encja departamentu
     * @return obiekt z informacjami o firmie
     */
    public static CompanyStructuresListElementDto toCompanyStructureListElement(Department department) {

        return CompanyStructuresListElementDto.builder()
                .id(department.getId())
                .name(department.getName())
                .description(department.getDescription())
                .companyName(department.getCompany().getName())
                .build();
    }

    /**
     * Metoda konwertująca encję {@link Office} na obiekt {@link CompanyStructuresListElementDto} przesyłany do widoku
     *
     * @param office encja biura
     * @return obiekt z informacjami o biurze
     */
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

    /**
     * Metoda konwertująca encję {@link Company} na obiekt {@link CompanyStructuresListElementDto} przesyłany do widoku
     *
     * @param company encja firmy
     * @return obiekt z informacjami o firmie
     */
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

    public static CompanyStructureViewDto toCompanyStructureViewDto(Company company){
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

    public static CompanyStructureViewDto toCompanyStructureViewDto(Department department){
        return CompanyStructureViewDto.builder()
                .id(department.getId())
                .name(department.getName())
                .description(department.getDescription())
                .companyName(department.getCompany().getName())
                .build();
    }

    public static CompanyStructureViewDto toCompanyStructureViewDto(Office office){
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

    /**
     * Metoda konwertująca encję {@link Office} na obiekt {@link CompanyStructuresListElementDto} przesyłany do widoku
     *
     * @param office encja biura
     * @return obiekt z informacjami o firmie
     */
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
                .parentName(office.getDepartment().getName())
                .verison(office.getVersion())
                .build();
    }

    public static Department toDepartment(CompanyStructureEditDto companyStructureEditDto, Department beforeDepartment, Company company) {
        beforeDepartment.setName(companyStructureEditDto.getName());
        beforeDepartment.setDescription(companyStructureEditDto.getDescription());
        beforeDepartment.setCompany(company);
        beforeDepartment.setVersion(companyStructureEditDto.getVerison());
        return beforeDepartment;
    }

    public static Company toCompany(CompanyStructureEditDto companyStructureEditDto, Company beforeCompany) {
        beforeCompany.setName(companyStructureEditDto.getName());
        beforeCompany.setDescription(companyStructureEditDto.getDescription());
        beforeCompany.getAddress().setCity(companyStructureEditDto.getCity());
        beforeCompany.getAddress().setStreet(companyStructureEditDto.getStreet());
        beforeCompany.getAddress().setBuildingNumber(companyStructureEditDto.getBuildingNumber());
        beforeCompany.getAddress().setFlatNumber(companyStructureEditDto.getFlatNumber());
        beforeCompany.setVersion(companyStructureEditDto.getVerison());
        return beforeCompany;
    }

    public static Office toOffice(CompanyStructureEditDto companyStructureEditDto, Office beforeOffice,Department department) {

        beforeOffice.setName(companyStructureEditDto.getName());
        beforeOffice.setDescription(companyStructureEditDto.getDescription());
        beforeOffice.getAddress().setCity(companyStructureEditDto.getCity());
        beforeOffice.getAddress().setStreet(companyStructureEditDto.getStreet());
        beforeOffice.getAddress().setBuildingNumber(companyStructureEditDto.getBuildingNumber());
        beforeOffice.getAddress().setFlatNumber(companyStructureEditDto.getFlatNumber());
        beforeOffice.setVersion(companyStructureEditDto.getVerison());
        beforeOffice.setDepartment(department);
        return beforeOffice;
    }

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

    public static Office toOffice(CompanyStructureAddDto companyStructureAddDto,Department department) {
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
