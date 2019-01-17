package com.stachura.praca_inz.backend.repository;

import com.stachura.praca_inz.backend.model.Parameter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParameterRepository extends JpaRepository<Parameter,Long> {
}
