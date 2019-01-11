package com.stachura.praca_inz.backend.repository;

import com.stachura.praca_inz.backend.model.Device;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceRepository extends CrudRepository<Device, Long> {

}
