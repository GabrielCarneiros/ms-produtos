package com.carneiro.ms_produtos.business.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorResponseDTO {

    private LocalDateTime timestamp;

    private Integer status;

    private String error;

    private String message;

    private List<String> details;
}
