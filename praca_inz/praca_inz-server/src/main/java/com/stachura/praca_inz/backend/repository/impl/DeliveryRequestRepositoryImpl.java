package com.stachura.praca_inz.backend.repository.impl;

import com.stachura.praca_inz.backend.model.DeliveryRequest;
import com.stachura.praca_inz.backend.repository.AbstractRepository;
import com.stachura.praca_inz.backend.repository.interfaces.DeliveryRequestRepository;
import org.springframework.stereotype.Repository;

@Repository
public class DeliveryRequestRepositoryImpl extends AbstractRepository<DeliveryRequest> implements DeliveryRequestRepository {

    public DeliveryRequestRepositoryImpl() {
        super(DeliveryRequest.class);
    }
}
