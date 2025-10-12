package com.clean.car.controller;

import com.clean.car.dto.ServicoResponseDto;
import com.clean.car.service.ServicoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/servicos")
public class ServicoController {

    private final ServicoService servicoService;

    @GetMapping
    public ResponseEntity<List<ServicoResponseDto>> listar(@RequestParam(name = "tipo", required = false) String tipo) {
        return tipo == null ? ResponseEntity.ok(servicoService.listar())
                : ResponseEntity.ok(servicoService.listarPorTipo(tipo));
    }
}