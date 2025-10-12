package com.clean.car.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class ServicoResponseDto {

    private String uuid;
    private String nome;
    private BigDecimal preco;
    private String descricao;
    private String descricaoCompleta;
    private String destaques;
    private String tempoMedio;
}