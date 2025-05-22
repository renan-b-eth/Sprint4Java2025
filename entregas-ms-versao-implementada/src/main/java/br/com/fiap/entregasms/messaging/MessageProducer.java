// src/main/java/br/com/fiap/entregasms/messaging/MessageProducer.java
package br.com.fiap.entregasms.messaging;

import br.com.fiap.entregasms.RabbitMQConfig; // Importe sua classe de configuração
import org.springframework.amqp.rabbit.core.RabbitTemplate; // Importe RabbitTemplate
import org.springframework.stereotype.Component;

/**
 * Componente que atua como produtor de mensagens.
 * Envia mensagens diretamente para a fila configurada no RabbitMQ.
 */
@Component
public class MessageProducer {

    private final RabbitTemplate rabbitTemplate;

    public MessageProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    /**
     * Envia uma mensagem para a fila definida.
     * @param message A mensagem a ser enviada.
     */
    public void sendMessage(String message) {
        // Envia a mensagem para a fila com o nome definido em RabbitMQConfig.QUEUE_NAME
        rabbitTemplate.convertAndSend(RabbitMQConfig.QUEUE_NAME, message);
        System.out.println("Mensagem enviada para a fila '" + RabbitMQConfig.QUEUE_NAME + "': " + message);
    }
}