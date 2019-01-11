package com.stachura.praca_inz.backend.repository;

import com.stachura.praca_inz.backend.model.Request;
import org.springframework.data.repository.CrudRepository;

public interface RequestRepository extends CrudRepository<Request,Long> {
}
