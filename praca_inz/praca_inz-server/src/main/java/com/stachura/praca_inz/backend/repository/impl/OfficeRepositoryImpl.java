package com.stachura.praca_inz.backend.repository.impl;

import com.stachura.praca_inz.backend.model.Company;
import com.stachura.praca_inz.backend.model.Office;
import com.stachura.praca_inz.backend.model.Office_;
import com.stachura.praca_inz.backend.repository.AbstractRepository;
import com.stachura.praca_inz.backend.repository.interfaces.OfficeRepository;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;

@Repository
public class OfficeRepositoryImpl extends AbstractRepository<Office> implements OfficeRepository {

    public OfficeRepositoryImpl() {
        super(Office.class);
    }


}
