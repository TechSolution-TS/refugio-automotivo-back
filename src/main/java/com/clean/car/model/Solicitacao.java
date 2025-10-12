package com.clean.car.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "solicitacoes", indexes = {
        @Index(name = "idx_solicitacoes_usuario_uuid", columnList = "usuario_uuid"),
        @Index(name = "idx_solicitacoes_servico_uuid", columnList = "servico_uuid")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Solicitacao {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 40)
    private String uuid;

    @Column(name = "usuario_uuid", nullable = false, length = 40)
    private String usuarioUuid;

    @Column(name = "servico_uuid", nullable = false, length = 40)
    private String servicoUuid;

    @CreationTimestamp
    @Column(name = "data_criacao", nullable = false)
    private LocalDateTime datarCriacao;

    @Column(name = "tipo")
    private String tipo;
}