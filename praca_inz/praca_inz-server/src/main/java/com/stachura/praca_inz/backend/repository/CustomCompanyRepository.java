package com.stachura.praca_inz.backend.repository;

import com.stachura.praca_inz.backend.model.Company;

public interface CustomCompanyRepository {

    void detachCompany(Company entity);

}
