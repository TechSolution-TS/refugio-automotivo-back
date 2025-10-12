package com.clean.car.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "endereco", indexes = {
        @Index(name = "uk_endereco_uuid", columnList = "uuid", unique = true)
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Endereco {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 40)
    private String uuid;

    @Column(nullable = false, length = 160)
    private String endereco;

    @Column(length = 80)
    private String bairro;

    @Column(length = 20)
    private String numero;

    @Column(length = 160)
    private String complemento;
}