package com.clean.car.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "servicos", indexes = {
        @Index(name = "uk_servicos_uuid", columnList = "uuid", unique = true)
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Servico {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 40)
    private String uuid;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal preco;

    @Column(length = 255)
    private String descricao;

    @Column(name = "descricao_completa", columnDefinition = "TEXT")
    private String descricaoCompleta;

    @Column(name = "destaques", columnDefinition = "TEXT")
    private String destaques;

    @Column(name = "tempo")
    private String tempo;

    @Column(name = "tipo")
    private String tipo;
}