package com.clean.car.service;

import com.clean.car.dto.EnderecoDto;
import com.clean.car.dto.SolicitacaoResponseDto;
import com.clean.car.dto.SolicitarLavagemRequestDto;
import com.clean.car.dto.UsuarioDto;
import com.clean.car.model.Endereco;
import com.clean.car.model.Servico;
import com.clean.car.model.Solicitacao;
import com.clean.car.model.Usuario;
import com.clean.car.repository.EnderecoRepository;
import com.clean.car.repository.ServicoRepository;
import com.clean.car.repository.SolicitacaoRepository;
import com.clean.car.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SolicitacaoService {

    private final UsuarioRepository usuarioRepository;
    private final EnderecoRepository enderecoRepository;
    private final ServicoRepository servicoRepository;
    private final SolicitacaoRepository solicitacaoRepository;
    private final TelegramNotificationService telegramNotificationService;

    @Transactional
    public SolicitacaoResponseDto solicitar(SolicitarLavagemRequestDto req) {
        Servico servico = servicoRepository.findByUuid(req.getServicoUuid())
                .orElseThrow(() -> new IllegalArgumentException("Serviço inválido: " + req.getServicoUuid()));

        Usuario usuario = upsertUsuario(req.getUsuario(), req.getEndereco());
        Endereco endereco = enderecoRepository.findByUuid(usuario.getEnderecoUuid())
                .orElseThrow(() -> new IllegalArgumentException("Endereço não encontrado"));

        Solicitacao sol = Solicitacao.builder()
                .uuid(UUID.randomUUID().toString())
                .usuarioUuid(usuario.getUuid())
                .servicoUuid(servico.getUuid())
                .tipo(servico.getTipo())
                .build();

        solicitacaoRepository.save(sol);

        try {
            telegramNotificationService.enviarNotificacaoNovaSolicitacao(usuario, endereco, servico);
        } catch (Exception e) {
            System.err.println("Erro ao enviar notificação Telegram: " + e.getMessage());
        }

        return SolicitacaoResponseDto.builder()
                .solicitacaoUuid(sol.getUuid())
                .usuarioUuid(usuario.getUuid())
                .servicoUuid(servico.getUuid())
                .build();
    }

    private Usuario upsertUsuario(UsuarioDto dto, EnderecoDto endDto) {
        Usuario usuario = usuarioRepository.findByCpf(dto.getCpf())
                .orElse(Usuario.builder()
                        .uuid(UUID.randomUUID().toString())
                        .cpf(dto.getCpf())
                        .build());

        usuario.setNome(dto.getNome());
        usuario.setTelefone(dto.getTelefone());

        Endereco endereco = null;
        if (usuario.getEnderecoUuid() != null) {
            endereco = enderecoRepository.findByUuid(usuario.getEnderecoUuid()).orElse(null);
        }
        if (endereco == null) {
            endereco = Endereco.builder()
                    .uuid(UUID.randomUUID().toString())
                    .build();
        }
        endereco.setEndereco(endDto.getEndereco());
        endereco.setBairro(endDto.getBairro());
        endereco.setNumero(endDto.getNumero());
        endereco.setComplemento(endDto.getComplemento());
        enderecoRepository.save(endereco);

        usuario.setEnderecoUuid(endereco.getUuid());
        return usuarioRepository.save(usuario);
    }
}