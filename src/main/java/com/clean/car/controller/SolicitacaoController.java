package com.clean.car.controller;

import com.clean.car.dto.SolicitacaoResponseDto;
import com.clean.car.dto.SolicitarLavagemRequestDto;
import com.clean.car.service.SolicitacaoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/solicitacoes")
public class SolicitacaoController {

    private final SolicitacaoService solicitacaoService;

    @PostMapping
    public ResponseEntity<SolicitacaoResponseDto> solicitar(@Valid @RequestBody SolicitarLavagemRequestDto req) {
        return ResponseEntity.ok(solicitacaoService.solicitar(req));
    }
}