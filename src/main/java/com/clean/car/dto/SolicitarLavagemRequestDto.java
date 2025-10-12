package com.clean.car.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class SolicitarLavagemRequestDto {
    @Valid @NotNull
    private UsuarioDto usuario;
    @Valid @NotNull
    private EnderecoDto endereco;
    @NotBlank
    private String servicoUuid;
    private String premiacao;
}