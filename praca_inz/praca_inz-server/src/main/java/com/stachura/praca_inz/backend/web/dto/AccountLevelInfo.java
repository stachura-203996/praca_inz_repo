package com.stachura.praca_inz.backend.web.dto;

import lombok.*;

/**
 * Obiekt przesyłany do widoku z informacjami o poziomach dostępu konta
 */
@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class AccountLevelInfo {

    private Long accessLevelId;
    private String name;
    private boolean active;
}
