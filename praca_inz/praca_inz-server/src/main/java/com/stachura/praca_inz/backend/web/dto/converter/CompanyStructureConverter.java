package com.stachura.praca_inz.backend.web.dto.converter;

import com.stachura.praca_inz.backend.model.Address;
import com.stachura.praca_inz.backend.model.Company;
import com.stachura.praca_inz.backend.model.Department;
import com.stachura.praca_inz.backend.model.Office;
import com.stachura.praca_inz.backend.web.dto.CompanyStructureAddDto;
import com.stachura.praca_inz.backend.web.dto.CompanyStructuresListElementDto;
import org.apache.commons.lang3.StringUtils;

/**
 * Dostarcza metod do konwersji obiektów związanych z strukturą firmy
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
                .parentName(department.getCompany().getName())
                .build();
    }

    /**
     * Metoda konwertująca encję {@link Office} na obiekt {@link CompanyStructuresListElementDto} przesyłany do widoku
     *
     * @param office encja biura
     * @return obiekt z informacjami o firmie
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
                .parentName(office.getDepartment().getName())
                .build();
    }


    public static Company toCompany(CompanyStructureAddDto companyStructureAddDto){
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
}
