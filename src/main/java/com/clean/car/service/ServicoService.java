package com.clean.car.service;

import com.clean.car.dto.ServicoResponseDto;
import com.clean.car.repository.ServicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServicoService {
    private final ServicoRepository servicoRepository;

    public List<ServicoResponseDto> listar() {
        return servicoRepository.findAll().stream()
                .map(s -> ServicoResponseDto.builder()
                        .uuid(s.getUuid())
                        .nome(s.getNome())
                        .preco(s.getPreco())
                        .descricao(s.getDescricao())
                        .descricaoCompleta(s.getDescricaoCompleta())
                        .destaques(s.getDestaques())
                        .tempoMedio(s.getTempo())
                        .build())
                .toList();
    }

    public List<ServicoResponseDto> listarPorTipo(String tipo) {
        return servicoRepository.findByTipo(tipo).stream()
                .map(s -> ServicoResponseDto.builder()
                        .uuid(s.getUuid())
                        .nome(s.getNome())
                        .preco(s.getPreco())
                        .descricao(s.getDescricao())
                        .descricaoCompleta(s.getDescricaoCompleta())
                        .destaques(s.getDestaques())
                        .tempoMedio(s.getTempo())
                        .build())
                .toList();
    }
}