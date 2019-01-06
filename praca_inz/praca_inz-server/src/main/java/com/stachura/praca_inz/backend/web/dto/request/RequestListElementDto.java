package com.stachura.praca_inz.backend.web.dto.request;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.stachura.praca_inz.backend.model.DeviceField;
import com.stachura.praca_inz.backend.model.Warehouse;
import com.stachura.praca_inz.backend.model.enums.RequestType;
import com.stachura.praca_inz.backend.model.enums.Status;
import com.stachura.praca_inz.backend.model.security.User;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class RequestListElementDto {
    private Long id;
    private String title;
    private String username;
    private String status;
    private String type;
    private boolean acceptedToSend;
    private boolean acceptedToRecive;
    private String recieverWarehouseName;
    private String senderWarehouseName;
    private String createDate;
    private long version;
}
