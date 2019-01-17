package com.stachura.praca_inz.backend.repository;

import com.stachura.praca_inz.backend.model.Device;
import com.stachura.praca_inz.backend.repository.custom_interface.CustomDeviceRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Long>, CustomDeviceRepository {

}
