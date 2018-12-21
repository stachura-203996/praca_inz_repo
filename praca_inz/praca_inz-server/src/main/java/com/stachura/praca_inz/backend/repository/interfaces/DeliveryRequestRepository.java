package com.stachura.praca_inz.backend.repository.interfaces;

import com.stachura.praca_inz.backend.exception.EntityException;
import com.stachura.praca_inz.backend.model.DeliveryRequest;
import java.util.List;

public interface DeliveryRequestRepository {

    DeliveryRequest find(Long id);

    List<DeliveryRequest> findAll();

    void create(DeliveryRequest office)throws EntityException;

    DeliveryRequest update(DeliveryRequest office)throws EntityException;

    void remove(Long id);

    void remove(DeliveryRequest office);
}
