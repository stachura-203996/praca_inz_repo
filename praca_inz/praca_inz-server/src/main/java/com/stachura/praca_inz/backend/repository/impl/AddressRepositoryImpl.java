package com.stachura.praca_inz.backend.repository.impl;

import com.stachura.praca_inz.backend.model.Address;
import com.stachura.praca_inz.backend.repository.AbstractRepository;
import com.stachura.praca_inz.backend.repository.interfaces.AddressRepository;
import org.springframework.stereotype.Repository;

@Repository
public class AddressRepositoryImpl extends AbstractRepository<Address> implements AddressRepository {

    protected AddressRepositoryImpl() {
        super(Address.class);
    }

}
