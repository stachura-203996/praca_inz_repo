package com.stachura.praca_inz.backend.service;

import com.stachura.praca_inz.backend.exception.base.AppBaseException;
import com.stachura.praca_inz.backend.web.dto.DeviceTypeListElementDto;

import java.util.List;

public interface DeviceTypeService {

    List<DeviceTypeListElementDto> getAllDeviceTypes();

    void createNewDeviceType(String type) throws AppBaseException;

    void deleteDeviceTypeById(Long id) throws AppBaseException;
}
