package com.stachura.praca_inz.backend.repository;

import com.stachura.praca_inz.backend.model.security.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole,Long> {

}
