package com.stachura.praca_inz.backend.repository.interfaces;

import com.stachura.praca_inz.backend.exception.EntityException;
import com.stachura.praca_inz.backend.model.Parameter;

import java.util.List;

public interface ParameterRepository {

    Parameter find(Long id);

    List<Parameter> findAll();

    void create(Parameter office)throws EntityException;

    Parameter update(Parameter office)throws EntityException;

    void remove(Long id);

    void remove(Parameter office);
}
