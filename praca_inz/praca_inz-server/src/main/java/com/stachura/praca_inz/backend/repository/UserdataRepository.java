package com.stachura.praca_inz.backend.repository;

import com.stachura.praca_inz.backend.model.Userdata;
import com.stachura.praca_inz.backend.model.security.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserdataRepository extends JpaRepository<Userdata, Long> {

    @Query("SELECT DISTINCT userdata FROM Userdata userdata " +
            "WHERE userdata.email = :email")
    Userdata findByEmail(@Param("email")String email);
}
