package com.stachura.praca_inz.backend.repository.impl;

import com.stachura.praca_inz.backend.model.Delivery;
import com.stachura.praca_inz.backend.repository.AbstractRepository;
import com.stachura.praca_inz.backend.repository.interfaces.DeliveryRepository;
import org.springframework.stereotype.Repository;

@Repository
public class DeliveryRepositoryImpl  extends AbstractRepository<Delivery> implements DeliveryRepository {

    public DeliveryRepositoryImpl() {
        super(Delivery.class);
    }
}
