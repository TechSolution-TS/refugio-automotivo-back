package com.clean.car.service;

import com.clean.car.dto.CadastroBarbeariaDto;
import com.clean.car.dto.ClienteResponseDto;
import com.clean.car.dto.EnderecoDto;
import com.clean.car.model.CadastroBarbearia;
import com.clean.car.model.Endereco;
import com.clean.car.model.Usuario;
import com.clean.car.repository.CadastroBarbeariaRepository;
import com.clean.car.repository.EnderecoRepository;
import com.clean.car.repository.SolicitacaoRepository;
import com.clean.car.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CadastroBarbeariaService {

    private final CadastroBarbeariaRepository cadastroBarbeariaRepository;

    @Transactional
    public String cadastrar(CadastroBarbeariaDto cadastro) {
       if (cadastroBarbeariaRepository.findByCpf(cadastro.getCpf()).isPresent()) {
           return "Sucesso ao cadastrar!";
       }

       cadastroBarbeariaRepository.save(CadastroBarbearia.builder()
                .uuid(UUID.randomUUID().toString())
                .cpf(cadastro.getCpf())
                .nome(cadastro.getNome())
                .telefone(cadastro.getTelefone())
                .endereco(cadastro.getEndereco())
                .idade(cadastro.getIdade())
                .build());

        return "Sucesso ao cadastrar!";
    }
}