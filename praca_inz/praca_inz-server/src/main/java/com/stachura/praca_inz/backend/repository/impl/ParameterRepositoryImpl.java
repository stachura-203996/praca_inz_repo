package com.stachura.praca_inz.backend.repository.impl;


import com.stachura.praca_inz.backend.exception.repository.DatabaseErrorException;
import com.stachura.praca_inz.backend.exception.repository.EntityException;
import com.stachura.praca_inz.backend.exception.repository.EntityValidationException;
import com.stachura.praca_inz.backend.model.Parameter;
import com.stachura.praca_inz.backend.repository.AbstractRepository;
import com.stachura.praca_inz.backend.repository.interfaces.ParameterRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolationException;

@Repository
public class ParameterRepositoryImpl extends AbstractRepository <Parameter> implements ParameterRepository {

    public ParameterRepositoryImpl() {
        super(Parameter.class);
    }

}
