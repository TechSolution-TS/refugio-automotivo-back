package com.clean.car.service;

import com.clean.car.model.Endereco;
import com.clean.car.model.Servico;
import com.clean.car.model.Usuario;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
@Service
public class TelegramNotificationService {

    @Value("${telegram.bot.token}")
    private String botToken;

    @Value("${telegram.bot.chat-id}")
    private String chatId;

    private final RestTemplate restTemplate = new RestTemplate();

    @Async
    public void enviarNotificacaoNovaSolicitacao(Usuario user, Endereco endereco, Servico servico) {
        try {
            String mensagem = construirMensagemSolicitacao(user, endereco, servico);
            enviarMensagem(mensagem);
        } catch (Exception e) {
            log.error("Erro ao enviar notificação Telegram: {}", e.getMessage(), e);
        }
    }

    private void enviarMensagem(String mensagem) {
        String url = UriComponentsBuilder
                .fromHttpUrl("https://api.telegram.org/bot" + botToken + "/sendMessage")
                .queryParam("chat_id", chatId)
                .queryParam("text", mensagem)
                .queryParam("parse_mode", "Markdown")
                .build()
                .toUriString();

        restTemplate.getForObject(url, String.class);
    }

    private String construirMensagemSolicitacao(Usuario user, Endereco endereco, Servico servico) {
        StringBuilder msg = new StringBuilder();
        msg.append("🚗 *NOVA SOLICITAÇÃO - REFUGIO AUTOMOTIVO* 🚗\n\n");

        msg.append("📋 *DADOS DO CLIENTE*\n");
        msg.append("👤 Nome: `").append(user.getNome()).append("`\n");
        msg.append("📱 Telefone: `").append(user.getTelefone()).append("`\n");
        msg.append("🆔 CPF: `").append(formatarCpf(user.getCpf())).append("`\n\n");

        msg.append("🧼 *SERVIÇO SOLICITADO*\n");
        msg.append("✨ Serviço: *").append(servico.getNome()).append("*\n");
        msg.append("💰 Valor: *R$ ").append(String.format("%.2f", servico.getPreco())).append("*\n\n");

        msg.append("📍 *ENDEREÇO DE COLETA*\n");
        msg.append("🏠 ").append(endereco.getEndereco()).append(", ").append(endereco.getNumero()).append("\n");
        msg.append("🗺️ Bairro: ").append(endereco.getBairro()).append("\n");

        if (endereco.getComplemento() != null && !endereco.getComplemento().isEmpty()) {
            msg.append("📝 Complemento: ").append(endereco.getComplemento()).append("\n");
        }

        msg.append("\n⏰ *Aguardando confirmação!*");
        msg.append("\n\n_Solicitação recebida em: ").append(java.time.LocalDateTime.now().format(
                java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")
        )).append("_");

        return msg.toString();
    }

    private String formatarCpf(String cpf) {
        if (cpf == null || cpf.length() != 11) {
            return cpf;
        }
        return cpf.substring(0, 3) + "." +
                cpf.substring(3, 6) + "." +
                cpf.substring(6, 9) + "-" +
                cpf.substring(9, 11);
    }

    @Async
    public void enviarMensagemCustomizada(String mensagem) {
        try {
            enviarMensagem(mensagem);
        } catch (Exception e) {
            log.error("Erro ao enviar mensagem customizada: {}", e.getMessage(), e);
        }
    }
}