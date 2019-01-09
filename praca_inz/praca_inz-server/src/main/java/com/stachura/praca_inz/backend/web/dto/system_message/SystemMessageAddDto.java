package com.stachura.praca_inz.backend.web.dto.system_message;

import lombok.*;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class SystemMessageAddDto {
    private String title;
    private String message;
    private String messageDate;
}
