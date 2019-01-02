package com.stachura.praca_inz.backend.repository.interfaces;

import com.stachura.praca_inz.backend.exception.repository.EntityException;
import com.stachura.praca_inz.backend.model.Report;

import java.util.List;

public interface ReportRepository {

    Report find(Long id);

    List<Report> findAll();

    void create(Report office)throws EntityException;

    void update(Report office)throws EntityException;

    void remove(Long id);

    void remove(Report office);
}
