package com.stachura.praca_inz.backend.repository.impl;


import com.stachura.praca_inz.backend.model.Parameter;
import com.stachura.praca_inz.backend.repository.AbstractRepository;
import com.stachura.praca_inz.backend.repository.interfaces.ParameterRepository;
import org.springframework.stereotype.Repository;

@Repository
public class ParameterRepositoryImpl extends AbstractRepository <Parameter> implements ParameterRepository {

    public ParameterRepositoryImpl() {
        super(Parameter.class);
    }
}
