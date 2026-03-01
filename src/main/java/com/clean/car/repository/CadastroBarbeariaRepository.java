package com.clean.car.repository;

import com.clean.car.model.CadastroBarbearia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CadastroBarbeariaRepository extends JpaRepository<CadastroBarbearia, Long> {

    Optional<CadastroBarbearia> findByCpf(String cpf);
}