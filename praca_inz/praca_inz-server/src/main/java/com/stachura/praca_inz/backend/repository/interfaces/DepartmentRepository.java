package com.stachura.praca_inz.backend.repository.interfaces;

import com.stachura.praca_inz.backend.exception.EntityException;
import com.stachura.praca_inz.backend.model.Department;

import java.util.List;

public interface DepartmentRepository {
    Department find(Long id);

    Department find(String name);

    List<Department> findAll();

    void create(Department department)throws EntityException;

    Department update(Department department) throws EntityException;

    void remove(Long id);

    void remove(Department department);
}
