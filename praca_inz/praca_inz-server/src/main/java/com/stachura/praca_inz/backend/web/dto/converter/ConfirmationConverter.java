package com.stachura.praca_inz.backend.web.dto.converter;

import com.stachura.praca_inz.backend.model.Confirmation;
import com.stachura.praca_inz.backend.model.security.User;
import com.stachura.praca_inz.backend.web.dto.confirmation.ConfirmationAddDto;
import com.stachura.praca_inz.backend.web.dto.confirmation.ConfirmationListElementDto;
import com.stachura.praca_inz.backend.web.dto.confirmation.ConfirmationViewDto;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ConfirmationConverter {

    //VIEW
    public static ConfirmationViewDto toReportViewElement(Confirmation confirmation){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return ConfirmationViewDto.builder()
                .id(confirmation.getId())
                .title(confirmation.getTitle())
                .sender(confirmation.getSender().getUsername())
                .receiver(confirmation.getReciever().getUsername())
                .description(confirmation.getDescription())
                .confirmationDate(formatter.format(confirmation.getCreateDate().getTime()))
                .senderName(confirmation.getSender().getUserdata().getName())
                .senderSurname(confirmation.getSender().getUserdata().getSurname())
                .recieverName(confirmation.getReciever().getUserdata().getName())
                .recieverSurname(confirmation.getReciever().getUserdata().getSurname())
                .build();
    }

    //LIST
    public static ConfirmationListElementDto toReportListElement(Confirmation confirmation){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return ConfirmationListElementDto.builder()
                .id(confirmation.getId())
                .title(confirmation.getTitle())
                .sender(confirmation.getSender().getUsername())
                .receiver(confirmation.getReciever().getUsername())
                .confirmationDate(formatter.format(confirmation.getCreateDate().getTime()))
                .senderName(confirmation.getSender().getUserdata().getName())
                .senderSurname(confirmation.getSender().getUserdata().getSurname())
                .recieverName(confirmation.getReciever().getUserdata().getName())
                .recieverSurname(confirmation.getReciever().getUserdata().getSurname())
                .build();
    }

    //ADD
    public static Confirmation toReport(ConfirmationAddDto confirmationAddDto, User reciever, User sender){
        Confirmation confirmation =new Confirmation();
        confirmation.setDisableReciever(false);
        confirmation.setDisableSender(false);
        confirmation.setCreateDate(Calendar.getInstance());
        confirmation.setDeleted(false);
        confirmation.setReciever(reciever);
        confirmation.setDescription(confirmationAddDto.getDescription());
        confirmation.setTitle(confirmationAddDto.getTitle());
        confirmation.setSender(sender);
        return confirmation;
    }

}
