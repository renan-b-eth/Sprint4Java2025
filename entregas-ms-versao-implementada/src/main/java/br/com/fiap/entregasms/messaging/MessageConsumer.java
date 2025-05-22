
package br.com.fiap.entregasms.messaging;

import br.com.fiap.entregasms.RabbitMQConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Componente que atua como consumidor de mensagens.
 * Escuta a fila definida e processa as mensagens recebidas.
 */
@Component
public class MessageConsumer {

    /**
     * Este método será invocado automaticamente quando uma mensagem for recebida
     * na fila especificada.
     * @param message A mensagem recebida da fila.
     */
    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME) // Escuta a fila com o nome definido
    public void receiveMessage(String message) {
        System.out.println("Mensagem recebida da fila: " + message);
        // Aqui você adicionaria a lógica real de processamento da mensagem
        // Ex: salvar no banco de dados, chamar outro serviço, etc.
        System.out.println("OPA SALVOU AQUI");
    }
}