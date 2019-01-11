package com.stachura.praca_inz.backend.repository.interfaces;

import com.stachura.praca_inz.backend.exception.repository.EntityException;
import com.stachura.praca_inz.backend.model.Address;

import java.util.List;

public interface AddressRepository {

    Address find(Long id);

    List<Address> findAll();

    void create(Address address)throws EntityException;

    void update(Address address)throws EntityException;

    void remove(Long id);

    void remove(Address address);
}
