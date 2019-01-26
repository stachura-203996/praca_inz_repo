package com.stachura.praca_inz.backend.web.dto.confirmation;

import lombok.*;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class ConfirmationListElementDto {

    private Long id;
    private String title;
    private String sender;
    private String receiver;
    private String confirmationDate;
    private String senderName;
    private String senderSurname;
    private String recieverName;
    private String recieverSurname;
}
