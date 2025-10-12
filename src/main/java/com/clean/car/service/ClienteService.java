package com.clean.car.service;

import com.clean.car.dto.ClienteResponseDto;
import com.clean.car.dto.EnderecoDto;
import com.clean.car.model.Endereco;
import com.clean.car.model.Usuario;
import com.clean.car.repository.EnderecoRepository;
import com.clean.car.repository.SolicitacaoRepository;
import com.clean.car.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final UsuarioRepository usuarioRepository;
    private final EnderecoRepository enderecoRepository;
    private final SolicitacaoRepository solicitacaoRepository;

    public ClienteResponseDto consultarPorCpf(String cpf) {
        Usuario usuario = usuarioRepository.findByCpf(cpf)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado para CPF " + cpf));

        EnderecoDto endDto = null;
        String enderecoUuid = usuario.getEnderecoUuid();
        if (enderecoUuid != null) {
            Endereco end = enderecoRepository.findByUuid(enderecoUuid).orElse(null);
            if (end != null) {
                endDto = new EnderecoDto();
                endDto.setEndereco(end.getEndereco());
                endDto.setBairro(end.getBairro());
                endDto.setNumero(end.getNumero());
                endDto.setComplemento(end.getComplemento());
            }
        }

        long total = solicitacaoRepository.countByUsuarioUuidAndTipo(usuario.getUuid(), "limpeza");

        return ClienteResponseDto.builder()
                .usuarioUuid(usuario.getUuid())
                .cpf(usuario.getCpf())
                .nome(usuario.getNome())
                .telefone(usuario.getTelefone())
                .enderecoUuid(enderecoUuid)
                .endereco(endDto)
                .totalSolicitacoes(total)
                .build();
    }
}