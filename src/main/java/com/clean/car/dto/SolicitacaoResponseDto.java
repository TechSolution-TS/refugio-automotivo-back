package com.clean.car.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class SolicitacaoResponseDto {

    private String solicitacaoUuid;
    private String usuarioUuid;
    private String servicoUuid;
}