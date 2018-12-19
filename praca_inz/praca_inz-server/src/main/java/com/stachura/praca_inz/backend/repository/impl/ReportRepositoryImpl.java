package com.stachura.praca_inz.backend.repository.impl;

import com.stachura.praca_inz.backend.model.Report;
import com.stachura.praca_inz.backend.repository.AbstractRepository;
import com.stachura.praca_inz.backend.repository.interfaces.ReportRepository;
import org.springframework.stereotype.Repository;

@Repository
public class ReportRepositoryImpl extends AbstractRepository<Report> implements ReportRepository {

    public ReportRepositoryImpl() {super(Report.class);}
}
