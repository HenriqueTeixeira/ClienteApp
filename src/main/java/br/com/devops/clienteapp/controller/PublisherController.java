package br.com.devops.clienteapp.controller;

import br.com.devops.clienteapp.dto.MessageDTO;
import br.com.devops.clienteapp.service.MessagePublisher;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/publish")
public class PublisherController {

    private final MessagePublisher messagePublisher;

    public PublisherController(MessagePublisher messagePublisher) {
        this.messagePublisher = messagePublisher;
    }

    @PostMapping
    public String sendMessage(@RequestBody MessageDTO message) {
        messagePublisher.sendMessage(message);
        return "✅ Mensagem enviada: " + message.getTitle();
    }
}


