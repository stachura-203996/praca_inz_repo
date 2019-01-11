package com.stachura.praca_inz.backend.repository;

import com.stachura.praca_inz.backend.model.Delivery;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryRepository extends CrudRepository<Delivery,Long> {
}
