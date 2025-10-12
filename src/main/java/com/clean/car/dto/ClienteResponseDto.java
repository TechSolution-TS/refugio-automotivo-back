package com.clean.car.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class ClienteResponseDto {

    private String usuarioUuid;
    private String cpf;
    private String nome;
    private String telefone;
    private String enderecoUuid;
    private EnderecoDto endereco;
    private long totalSolicitacoes;
}