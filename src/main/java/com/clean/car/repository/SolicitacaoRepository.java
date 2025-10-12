package com.clean.car.repository;

import com.clean.car.model.Solicitacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SolicitacaoRepository extends JpaRepository<Solicitacao, Long> {
    long countByUsuarioUuidAndTipo(String usuarioUuid, String tipo);
}