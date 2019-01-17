package com.stachura.praca_inz.backend.repository;

import com.stachura.praca_inz.backend.model.Office;
import com.stachura.praca_inz.backend.repository.custom_interface.CustomOfficeRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OfficeRepository extends JpaRepository<Office,Long> , CustomOfficeRepository {
}
