package com.stachura.praca_inz.backend.repository;

import com.stachura.praca_inz.backend.model.security.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{

    @Query("SELECT DISTINCT user FROM User user " +
            "INNER JOIN FETCH user.userRoles AS roles " +
            "WHERE user.username = :username")
    Optional<User> findByUsername(@Param("username") String username);

}
