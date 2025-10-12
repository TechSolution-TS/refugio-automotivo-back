package com.clean.car.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EnderecoDto {

    @NotBlank private String endereco;
    private String bairro;
    private String numero;
    private String complemento;
}