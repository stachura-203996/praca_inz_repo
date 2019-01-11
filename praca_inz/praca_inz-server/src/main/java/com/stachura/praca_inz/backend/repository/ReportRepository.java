package com.stachura.praca_inz.backend.repository;

import com.stachura.praca_inz.backend.model.Report;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportRepository extends CrudRepository<Report, Long> {

}
