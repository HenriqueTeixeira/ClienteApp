package br.com.devops.clienteapp.service;

import br.com.devops.clienteapp.config.RabbitMqConfig;
import br.com.devops.clienteapp.dto.MessageDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessagePublisher {

    private final RabbitTemplate rabbitTemplate;

    public MessagePublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(MessageDTO message) {
        System.out.println("📤 Tentando enviar mensagem para RabbitMQ: " + message);

        try {
            rabbitTemplate.convertAndSend(RabbitMqConfig.EXCHANGE_NAME, RabbitMqConfig.QUEUE_NAME, message);
            System.out.println("✅ Mensagem enviada com sucesso para a fila: " + RabbitMqConfig.QUEUE_NAME);
        } catch (Exception e) {
            System.err.println("❌ ERRO ao enviar mensagem para RabbitMQ: " + e.getMessage());
        }
    }
}