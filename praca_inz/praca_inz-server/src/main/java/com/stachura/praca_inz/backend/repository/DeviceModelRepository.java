package com.stachura.praca_inz.backend.repository;

import com.stachura.praca_inz.backend.model.DeviceModel;
import com.stachura.praca_inz.backend.repository.custom_interface.CustomDeviceModelRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceModelRepository extends JpaRepository<DeviceModel,Long> , CustomDeviceModelRepository {

}
