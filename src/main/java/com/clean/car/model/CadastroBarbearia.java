package com.clean.car.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "barbearia")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CadastroBarbearia {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String uuid;

    @Column(nullable = false)
    private String cpf;

    @Column()
    private String nome;

    @Column(length = 20)
    private String telefone;

    private Integer idade;

    @Column(columnDefinition = "TEXT")
    private String endereco;

    @CreationTimestamp
    @Column(name = "data_criacao", nullable = false)
    private LocalDateTime datarCriacao;
}