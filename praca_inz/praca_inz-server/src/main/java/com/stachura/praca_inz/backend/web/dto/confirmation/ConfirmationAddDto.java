package com.stachura.praca_inz.backend.web.dto.confirmation;

import lombok.*;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class ConfirmationAddDto {
    String title;
    String description;
    Long reciever;
}
