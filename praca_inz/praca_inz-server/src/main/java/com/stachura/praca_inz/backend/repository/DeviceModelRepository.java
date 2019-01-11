package com.stachura.praca_inz.backend.repository;

import com.stachura.praca_inz.backend.model.DeviceModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceModelRepository extends CrudRepository<DeviceModel,Long> {

}
