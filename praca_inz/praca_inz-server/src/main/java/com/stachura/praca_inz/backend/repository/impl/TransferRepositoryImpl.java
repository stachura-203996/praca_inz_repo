package com.stachura.praca_inz.backend.repository.impl;

import com.stachura.praca_inz.backend.model.Transfer;
import com.stachura.praca_inz.backend.repository.AbstractRepository;
import com.stachura.praca_inz.backend.repository.interfaces.TransferRepository;
import org.springframework.stereotype.Repository;

@Repository
public class TransferRepositoryImpl extends AbstractRepository<Transfer> implements TransferRepository {

    public TransferRepositoryImpl() {
        super(Transfer.class);
    }

}
