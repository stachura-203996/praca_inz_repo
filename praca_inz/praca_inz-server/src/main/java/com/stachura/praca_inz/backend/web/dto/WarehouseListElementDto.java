package com.stachura.praca_inz.backend.web.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.stachura.praca_inz.backend.model.Device;
import com.stachura.praca_inz.backend.model.Office;
import com.stachura.praca_inz.backend.model.security.User;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class WarehouseListElementDto {

    private Long id;
    private String name;
    private String username;
    private String officeId;
    private String officeName;
    private String devicesNumber;

}
