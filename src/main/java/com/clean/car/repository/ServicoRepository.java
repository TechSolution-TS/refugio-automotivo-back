package com.clean.car.repository;

import com.clean.car.model.Servico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public interface ServicoRepository extends JpaRepository<Servico, Long> {
    Optional<Servico> findByUuid(String uuid);

    List<Servico> findByTipo(String tipo);
}