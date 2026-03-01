package com.clean.car.controller;

import com.clean.car.dto.CadastroBarbeariaDto;
import com.clean.car.dto.ClienteResponseDto;
import com.clean.car.model.CadastroBarbearia;
import com.clean.car.service.CadastroBarbeariaService;
import com.clean.car.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cadastro")
public class CadastroBarbeariaController {

    private final CadastroBarbeariaService cadastroBarbeariaService;

    @PostMapping()
    public ResponseEntity<String> cadastrar(@RequestBody CadastroBarbeariaDto cadastro) {
        return ResponseEntity.ok(cadastroBarbeariaService.cadastrar(cadastro));
    }
}