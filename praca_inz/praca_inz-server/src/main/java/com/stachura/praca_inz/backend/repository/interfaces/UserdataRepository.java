package com.stachura.praca_inz.backend.repository.interfaces;

import com.stachura.praca_inz.backend.exception.repository.EntityException;
import com.stachura.praca_inz.backend.model.Userdata;
import com.stachura.praca_inz.backend.model.security.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface UserdataRepository {


    Userdata findByEmail(String email);

    Userdata find(Long id);

    List<Userdata> findAll();

    void create(Userdata userdata)throws EntityException;

    void update(Userdata userdata)throws EntityException;

    void remove(Long id);

    void remove(Userdata userdata);
}
