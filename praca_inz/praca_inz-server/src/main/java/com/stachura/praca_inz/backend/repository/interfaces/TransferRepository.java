package com.stachura.praca_inz.backend.repository.interfaces;

import com.stachura.praca_inz.backend.exception.EntityException;
import com.stachura.praca_inz.backend.model.Transfer;

import java.util.List;

public interface TransferRepository {
    Transfer find(Long id);

    List<Transfer> findAll();

    void create(Transfer office)throws EntityException;

    Transfer update(Transfer office)throws EntityException;

    void remove(Long id);

    void remove(Transfer office);
}
