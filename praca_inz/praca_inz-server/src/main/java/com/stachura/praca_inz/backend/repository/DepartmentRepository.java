package com.stachura.praca_inz.backend.repository;

import com.stachura.praca_inz.backend.model.Department;

import java.util.List;

public interface DepartmentRepository {
    Department find(Long id);

    Department find(String name);

    List<Department> findAll();

    void create(Department department);

    Department update(Department department);

    void delete(Long id);

    void delete(Department department);
}
