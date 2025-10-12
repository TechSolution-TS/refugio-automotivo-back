package com.clean.car.controller;

import com.clean.car.dto.ClienteResponseDto;
import com.clean.car.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    @GetMapping("/{cpf}")
    public ResponseEntity<ClienteResponseDto> consultar(@PathVariable String cpf) {
        return ResponseEntity.ok(clienteService.consultarPorCpf(cpf.replaceAll("\\D", "")));
    }
}