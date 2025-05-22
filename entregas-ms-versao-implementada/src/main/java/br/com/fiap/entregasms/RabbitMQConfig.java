
package br.com.fiap.entregasms;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    // Nome da fila que usaremos
    public static final String QUEUE_NAME = "minha-fila-simples";

    // Define a fila no RabbitMQ
    @Bean
    public Queue queue() {
        // O nome da fila será "minha-fila-simples"
        // 'durable(true)' significa que a fila sobreviverá a reinícios do RabbitMQ
        return new Queue(QUEUE_NAME, true);
    }

    // O RabbitTemplate é autoconfigurado pelo Spring Boot se o spring-boot-starter-amqp estiver presente.
    // Você só precisa injetá-lo para enviar mensagens.
    // public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
    //     RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
    //     // Configuracoes adicionais podem ser feitas aqui, se necessário
    //     return rabbitTemplate;
    // }
}