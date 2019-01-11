package com.stachura.praca_inz.backend.repository;

import com.stachura.praca_inz.backend.model.Userdata;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserdataRepository extends CrudRepository<Userdata,Long> {

}
