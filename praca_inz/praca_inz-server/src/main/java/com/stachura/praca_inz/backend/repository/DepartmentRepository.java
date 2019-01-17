package com.stachura.praca_inz.backend.repository;

import com.stachura.praca_inz.backend.model.Department;
import com.stachura.praca_inz.backend.repository.custom_interface.CustomDepartmentRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department,Long> , CustomDepartmentRepository {
}
