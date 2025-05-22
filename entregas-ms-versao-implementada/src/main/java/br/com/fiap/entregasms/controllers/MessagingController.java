// src/main/java/br/com/fiap/entregasms/controller/MessagingController.java
package br.com.fiap.entregasms.controllers;

import br.com.fiap.entregasms.messaging.MessageProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador REST para enviar mensagens para a fila.
 * Simplesmente para demonstração e teste.
 */
@RestController
public class MessagingController {

    private final MessageProducer messageProducer;

    public MessagingController(MessageProducer messageProducer) {
        this.messageProducer = messageProducer;
    }

    // Exemplo de uso: http://localhost:8080/send-message?content=OlaMundoRabbit
    @GetMapping("/send-message")
    public ResponseEntity<String> sendMessage(@RequestParam String content) {
        messageProducer.sendMessage(content);
        return ResponseEntity.ok("Mensagem '" + content + "' enviada para a fila RabbitMQ.");
    }
}