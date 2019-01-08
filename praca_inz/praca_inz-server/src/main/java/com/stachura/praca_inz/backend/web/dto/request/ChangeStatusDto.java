package com.stachura.praca_inz.backend.web.dto.request;

import lombok.*;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class ChangeStatusDto {
    Long id;
    String status;
    long version;
}
