package com.stachura.praca_inz.backend.repository;

import com.stachura.praca_inz.backend.model.SystemMessage;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SystemMessageRepository extends CrudRepository<SystemMessage,Long> {
}
