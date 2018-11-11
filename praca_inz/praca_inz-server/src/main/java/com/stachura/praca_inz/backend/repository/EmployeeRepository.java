package com.stachura.praca_inz.backend.repository;

import com.stachura.praca_inz.backend.model.Employee;
import com.stachura.praca_inz.backend.model.security.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EmployeeRepository {

    @Query("SELECT DISTINCT employee FROM Employee employee " +
            "WHERE employee.email = :email")
    Employee findByEmail(@Param("email")String email);
}
