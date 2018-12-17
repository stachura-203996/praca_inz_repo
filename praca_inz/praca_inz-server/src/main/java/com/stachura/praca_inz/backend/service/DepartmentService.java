package com.stachura.praca_inz.backend.service;

import com.stachura.praca_inz.backend.model.Department;

import java.util.List;

public interface DepartmentService {
    Department get(Long id);

    Department get(String name);

    List<Department> getAll();

    void create(Department department);

    Department update(Department department);

    void delete(Long id);

    void delete(Department department);
}
