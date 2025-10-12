package com.clean.car.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "usuarios", indexes = {
        @Index(name = "uk_usuarios_uuid", columnList = "uuid", unique = true),
        @Index(name = "uk_usuarios_cpf", columnList = "cpf", unique = true)
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usuario {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 40)
    private String uuid;

    @Column(nullable = false, unique = true, length = 14)
    private String cpf;

    @Column(nullable = false, length = 120)
    private String nome;

    @Column(length = 20)
    private String telefone;

    @Column(name = "endereco_uuid", length = 40)
    private String enderecoUuid;

    @CreationTimestamp
    @Column(name = "data_criacao", nullable = false)
    private LocalDateTime datarCriacao;
}