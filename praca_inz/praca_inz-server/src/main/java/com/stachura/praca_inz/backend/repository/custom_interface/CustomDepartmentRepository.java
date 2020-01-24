package com.stachura.praca_inz.backend.repository.custom_interface;

import com.stachura.praca_inz.backend.model.Department;

public interface CustomDepartmentRepository {

    void detach(Department department);
}
