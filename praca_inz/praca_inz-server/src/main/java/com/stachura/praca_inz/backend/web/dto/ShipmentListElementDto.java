package com.stachura.praca_inz.backend.web.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.stachura.praca_inz.backend.model.Device;
import com.stachura.praca_inz.backend.model.Warehouse;
import com.stachura.praca_inz.backend.model.enums.Status;
import lombok.*;

import javax.persistence.*;
import java.util.Collection;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class ShipmentListElementDto {

    private Long id = null;
    private String deliveryNumber;
    private String title;
    private String username;
    private String status;
    private String senderWarehouseName;
    private String recieverWarehouseName;
    private String utilTimestamp;

}
