package com.stachura.praca_inz.backend.repository.custom_interface;

import com.stachura.praca_inz.backend.model.Company;

public interface CustomCompanyRepository {

    void detach(Company company);
}
