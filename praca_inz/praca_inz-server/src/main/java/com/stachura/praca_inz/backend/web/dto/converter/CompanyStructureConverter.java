package com.stachura.praca_inz.backend.web.dto.converter;

import com.stachura.praca_inz.backend.model.Address;
import com.stachura.praca_inz.backend.model.Company;
import com.stachura.praca_inz.backend.model.Department;
import com.stachura.praca_inz.backend.model.Office;
import com.stachura.praca_inz.backend.repository.interfaces.CompanyRepository;
import com.stachura.praca_inz.backend.repository.interfaces.DepartmentRepository;
import com.stachura.praca_inz.backend.web.dto.CompanyStructureAddDto;
import com.stachura.praca_inz.backend.web.dto.CompanyStructureEditDto;
import com.stachura.praca_inz.backend.web.dto.CompanyStructuresListElementDto;

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
                .zipCode(company.getAddress().getZipCode())
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
                .zipCode(office.getAddress().getZipCode())
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
                .zipCode(company.getAddress().getZipCode())
                .build();
    }

    public static CompanyStructureEditDto toCompanyStructureEdit(Department department) {
        return CompanyStructureEditDto.builder()
                .id(department.getId())
                .name(department.getName())
                .description(department.getDescription())
                .parentId(String.valueOf(department.getCompany().getId()))
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
                .zipCode(office.getAddress().getZipCode())
                .parentId(String.valueOf(office.getDepartment().getId()))
                .build();
    }

    public static Department toDepartment(CompanyStructureEditDto companyStructureEditDto, Department beforeDepartment) {
        beforeDepartment.setName(companyStructureEditDto.getName());
        beforeDepartment.setDescription(companyStructureEditDto.getDescription());
        return beforeDepartment;
    }

    public static Company toCompany(CompanyStructureEditDto companyStructureEditDto, Company beforeCompany) {
        beforeCompany.setName(companyStructureEditDto.getName());
        beforeCompany.setDescription(companyStructureEditDto.getDescription());
        beforeCompany.getAddress().setCity(companyStructureEditDto.getCity());
        beforeCompany.getAddress().setStreet(companyStructureEditDto.getStreet());
        beforeCompany.getAddress().setBuildingNumber(companyStructureEditDto.getBuildingNumber());
        beforeCompany.getAddress().setFlatNumber(companyStructureEditDto.getFlatNumber());
        beforeCompany.getAddress().setZipCode(companyStructureEditDto.getZipCode());
        return beforeCompany;
    }

    public static Office toOffice(CompanyStructureEditDto companyStructureEditDto, Office beforeOffice) {

        beforeOffice.setName(companyStructureEditDto.getName());
        beforeOffice.setDescription(companyStructureEditDto.getDescription());
        beforeOffice.getAddress().setCity(companyStructureEditDto.getCity());
        beforeOffice.getAddress().setStreet(companyStructureEditDto.getStreet());
        beforeOffice.getAddress().setBuildingNumber(companyStructureEditDto.getBuildingNumber());
        beforeOffice.getAddress().setFlatNumber(companyStructureEditDto.getFlatNumber());
        beforeOffice.getAddress().setZipCode(companyStructureEditDto.getZipCode());
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
        adress.setZipCode(companyStructureAddDto.getZipCode());
        company.setAddress(adress);
        return company;
    }

    public static Department toDepartment(CompanyStructureAddDto companyStructureAddDto, CompanyRepository companyRepository) {
        Department department = new Department();
        department.setName(companyStructureAddDto.getName());
        department.setDescription(companyStructureAddDto.getDescription());
        department.setCompany(companyRepository.find(companyStructureAddDto.getCompanyId()));
        return department;
    }

    public static Office toOffice(CompanyStructureAddDto companyStructureAddDto,DepartmentRepository departmentRepository) {
        Office office = new Office();
        office.setDepartment(departmentRepository.find(companyStructureAddDto.getDepartmentId()));
        office.setName(companyStructureAddDto.getName());
        office.setDescription(companyStructureAddDto.getDescription());
        Address adress = new Address();
        adress.setCity(companyStructureAddDto.getCity());
        adress.setStreet(companyStructureAddDto.getStreet());
        adress.setBuildingNumber(companyStructureAddDto.getBuildingNumber());
        adress.setFlatNumber(companyStructureAddDto.getFlatNumber());
        adress.setZipCode(companyStructureAddDto.getZipCode());
        office.setAddress(adress);
        return office;
    }
}
