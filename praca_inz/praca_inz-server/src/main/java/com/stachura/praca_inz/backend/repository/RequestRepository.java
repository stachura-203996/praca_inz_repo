package com.stachura.praca_inz.backend.repository;

import com.stachura.praca_inz.backend.model.Request;
import com.stachura.praca_inz.backend.repository.custom_interface.CustomRequestRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestRepository extends JpaRepository<Request,Long> , CustomRequestRepository {
}
