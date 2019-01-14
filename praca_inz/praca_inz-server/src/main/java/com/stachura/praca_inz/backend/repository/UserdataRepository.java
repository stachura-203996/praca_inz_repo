package com.stachura.praca_inz.backend.repository;

import com.stachura.praca_inz.backend.model.Userdata;
import com.stachura.praca_inz.backend.model.security.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserdataRepository extends CrudRepository<Userdata,Long> {


    @Query("SELECT DISTINCT user FROM Userdata user " +
            "WHERE user.email = :email")
    Optional<Userdata> findByEmail(@Param("email") String email);
}
