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

    Long id;
    String title;
    String shipmentNumber;
    String username;
    String status;
    String createDate;
    String lastUpdate;
    String sender;
    String receiver;

}
