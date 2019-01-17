package com.stachura.praca_inz.backend.repository;

import com.stachura.praca_inz.backend.model.Company;
import com.stachura.praca_inz.backend.repository.custom_interface.CustomCompanyRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company,Long>, CustomCompanyRepository {

}
