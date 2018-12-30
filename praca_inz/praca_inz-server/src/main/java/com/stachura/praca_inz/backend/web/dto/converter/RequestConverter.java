package com.stachura.praca_inz.backend.web.dto.converter;

import com.stachura.praca_inz.backend.model.Request;
import com.stachura.praca_inz.backend.web.dto.RequestListElementDto;

public class RequestConverter {

    public static RequestListElementDto toRequestListElement(Request request) {
        return RequestListElementDto.builder()
                .id(request.getId())
                .title(request.getTitle())
                .username(request.getUser().getUsername())
                .status(request.getStatus().name())
                .type(request.getRequestType().name())
                .acceptedToSend(request.isAcceptedToSend())
                .acceptedToRecive(request.isAcceptedToRecive())
                .recieverWarehouseName(request.getRecieverWarehouse().getName())
                .senderWarehouseName(request.getSenderWarehouse().getName())
                .utilTimestamp(request.getUtilTimestamp().toString())
                .build();
    }
}
