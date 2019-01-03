package com.stachura.praca_inz.backend.repository.interfaces;

import com.stachura.praca_inz.backend.exception.repository.EntityException;
import com.stachura.praca_inz.backend.model.Delivery;
import java.util.List;

public interface DeliveryRepository {

    Delivery find(Long id);

    List<Delivery> findAll();

    void create(Delivery office)throws EntityException;

    void update(Delivery office)throws EntityException;

    void remove(Long id);

    void remove(Delivery office);
}
