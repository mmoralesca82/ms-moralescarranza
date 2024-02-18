package com.codigo.msmoralescarranza.domain.aggregates.response;

import lombok.*;
import org.springframework.stereotype.Component;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseError {
        private int code;
        private String message;
}
