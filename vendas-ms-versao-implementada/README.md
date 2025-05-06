# Mensageria com Spring Boot e ActiveMQ

### Excalidraw

[Aula do dia 15/04/2025](https://link.excalidraw.com/p/readonly/Ib7x4fbckPXCD7VdHVDW)

### Tecnologias utilizadas neste repositório

* Spring MVC
* Thymeleaf
* Spring Data JPA
* Internacionalização
* Spring Security com OAuth2
* Messaging (O conteúdo foco)
* Spring Cloud OpenFeign

### 1. Comunicação Síncrona vs. Assíncrona

#### 1.1 Comunicação Síncrona

Na comunicação síncrona, o remetente envia uma requisição e aguarda uma resposta do destinatário antes de continuar sua execução. É um modelo de "solicitação-resposta" direto.

**Características:**

* **Acoplamento forte:** O remetente e o destinatário precisam estar online e disponíveis ao mesmo tempo.
* **Bloqueio:** O remetente fica bloqueado aguardando a resposta, o que pode levar a latência e impactar a performance.
* **Simplicidade:** A implementação inicial pode ser mais simples, pois o fluxo de execução é linear.

**Exemplo Comum:** Chamadas REST tradicionais entre microsserviços.

#### 1.2 Comunicação Assíncrona

Na comunicação assíncrona, o remetente envia uma mensagem e não espera por uma resposta imediata. A mensagem é geralmente armazenada em um intermediário (como um broker de mensagens) e o destinatário a processa quando estiver disponível.

**Características:**

* **Acoplamento fraco:** O remetente e o destinatário não precisam estar online simultaneamente.
* **Não bloqueante:** O remetente pode continuar suas operações após enviar a mensagem, melhorando a performance e a capacidade de resposta.
* **Resiliência:** Se um dos serviços estiver temporariamente indisponível, as mensagens podem ser enfileiradas e processadas posteriormente.
* **Escalabilidade:** Facilita a escalabilidade, pois os serviços podem processar mensagens em seu próprio ritmo.
* **Complexidade:** A implementação pode ser mais complexa, envolvendo o gerenciamento do broker de mensagens e o tratamento de possíveis falhas no envio ou recebimento.

**Exemplo Comum:** Utilização de filas de mensagens (como ActiveMQ, RabbitMQ, Kafka) para comunicar eventos ou tarefas entre microsserviços.

### 2. Filas vs. Tópicos

Dentro do paradigma da mensageria, existem dois modelos principais de troca de mensagens: filas e tópicos.

#### 2.1 Filas (Queues)

Uma fila representa um canal de comunicação ponto-a-ponto. Cada mensagem enviada para uma fila é consumida por **apenas um** dos consumidores inscritos. As filas garantem que cada mensagem seja processada exatamente uma vez (na maioria dos cenários).

**Características:**

* **Ponto-a-ponto:** Uma mensagem é destinada a um único consumidor.
* **Garantia de entrega (geralmente):** Os brokers de mensagens geralmente garantem que a mensagem será entregue e processada (pelo menos uma vez).
* **Balanceamento de carga:** Se houver múltiplos consumidores na mesma fila, as mensagens podem ser distribuídas entre eles, ajudando no balanceamento de carga.

**Analogia:** Uma fila de atendimento em um banco. Cada cliente (mensagem) é atendido por um único caixa (consumidor) disponível.

#### 2.2 Tópicos (Topics)

Um tópico representa um canal de comunicação de publicação e subscrição (publish-subscribe). Cada mensagem enviada para um tópico é replicada e entregue a **todos** os consumidores que se inscreveram nesse tópico.

**Características:**

* **Publicação e Subscrição:** Um produtor publica uma mensagem em um tópico, e múltiplos consumidores que se inscreveram nesse tópico recebem uma cópia da mensagem.
* **Broadcasting:** Permite enviar a mesma mensagem para múltiplos interessados.
* **Acoplamento fraco:** Os produtores não precisam conhecer os consumidores, e vice-versa.

**Analogia:** Um sistema de notícias. Um editor (produtor) publica uma notícia (mensagem) em um tópico (canal de notícias), e todos os assinantes (consumidores) desse canal recebem a notícia.

### 3. ActiveMQ: Um Broker de Mensagens JMS

Apache ActiveMQ é um popular broker de mensagens de código aberto que implementa as especificações Java Message Service (JMS). Ele permite a comunicação assíncrona confiável entre diferentes aplicações e sistemas.

**Principais Características do ActiveMQ:**

* **Suporte a JMS:** Implementa as APIs JMS 1.1 e 2.0, fornecendo uma interface padrão para trabalhar com mensageria em Java.
* **Suporte a múltiplos protocolos:** Além do protocolo nativo OpenWire, suporta outros protocolos como AMQP, MQTT e STOMP, permitindo a integração com diversas tecnologias.
* **Filas e Tópicos:** Oferece suporte completo tanto para filas (ponto-a-ponto) quanto para tópicos (publish-subscribe).
* **Persistência de Mensagens:** Permite configurar a persistência das mensagens em disco, garantindo que as mensagens não sejam perdidas em caso de falha do broker.
* **Transações:** Suporta transações para garantir a entrega atômica de mensagens.
* **Clustering:** Permite configurar clusters de brokers para alta disponibilidade e escalabilidade.
* **Interface de Administração Web:** Fornece uma interface web para monitorar e gerenciar o broker, filas e tópicos.

### 4. Passo a Passo: Implementando ActiveMQ com Spring Boot para Comunicar Microsserviços

O passo a passo a seguir não é o exemplo que abordaremos em sala, dessa maneira é mais um exercício para ratificar o conteúdo. Faça com calma, observando atentamente as etapas.
Vamos criar um exemplo prático para demonstrar como utilizar o ActiveMQ e o Spring Boot para permitir a comunicação assíncrona entre dois microsserviços.

**Pré-requisitos:**

* Java Development Kit (JDK) instalado.
* Maven instalado.
* Um editor de código (IntelliJ IDEA, VS Code, etc.).
* ActiveMQ instalado e em execução. Você pode baixar o ActiveMQ em [https://activemq.apache.org/download.html](https://activemq.apache.org/download.html) e executá-lo seguindo as instruções na documentação. Por padrão, ele roda na porta 61616 (protocolo OpenWire) e possui uma interface de administração web em `http://localhost:8161/admin` (credenciais padrão: `admin/admin`). 
  * Observação: No repositório [entregas-ms](https://github.com/fiap-2tdsps/entregas-ms) temos um arquivo docker-compose para inicializar o ActiveMQ. 

**Arquitetura do Exemplo:**

Teremos dois microsserviços Spring Boot:

* **Serviço Produtor:** Responsável por enviar mensagens para uma fila no ActiveMQ.
* **Serviço Consumidor:** Responsável por receber e processar as mensagens da fila no ActiveMQ.

#### 4.1 Criando o Serviço Produtor

1.  **Crie um novo projeto Spring Boot (usando Spring Initializr ou seu IDE):**
    * GroupId: `com.example`
    * ArtifactId: `producer-service`
    * Adicione a dependência: `Spring Web` e `Spring Boot Starter ActiveMQ`.

2.  **Configure o ActiveMQ no `application.properties`:**

    ```properties
    spring.activemq.broker-url=tcp://localhost:61616
    spring.activemq.user=admin
    spring.activemq.password=admin
    ```

3.  **Crie uma classe para representar a mensagem (opcional, mas recomendado):**

    ```java
    package com.example.producerservice.model;

    public class Order {
        private String orderId;
        private String customerId;
        private double amount;

        // Construtores, getters e setters
        public Order() {
        }

        public Order(String orderId, String customerId, double amount) {
            this.orderId = orderId;
            this.customerId = customerId;
            this.amount = amount;
        }

       //getters e setters
    }
    ```

4.  **Crie um serviço para enviar mensagens:**

    ```java
    package com.example.producerservice.service;

    import com.example.producerservice.model.Order;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.jms.core.JmsTemplate;
    import org.springframework.stereotype.Service;

    @Service
    public final class OrderProducerService {
        
        private final JmsTemplate jmsTemplate;

        private static final String ORDER_QUEUE = "order.queue";
    
        public OrderProducerService(JmsTemplate jmsTemplate) {
            this.jmsTemplate = jmsTemplate;
         }


        public void sendOrder(Order order) {
            System.out.println("Enviando pedido: " + order);
            jmsTemplate.convertAndSend(ORDER_QUEUE, order);
        }
    }
    ```

    * `private final JmsTemplate jmsTemplate;`: O `JmsTemplate` é uma classe do Spring que simplifica a interação com o JMS.
    * `private static final String ORDER_QUEUE = "order.queue";`: Define o nome da fila para onde as mensagens serão enviadas.
    * `jmsTemplate.convertAndSend(ORDER_QUEUE, order);`: Envia o objeto `order` para a fila `order.queue`. O `convertAndSend` automaticamente converte o objeto para um formato adequado para a mensagem (por padrão, usa serialização Java).

5.  **Crie um controller para expor um endpoint que envia pedidos:**

    ```java
    package com.example.producerservice.controller;

    import com.example.producerservice.model.Order;
    import com.example.producerservice.service.OrderProducerService;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.web.bind.annotation.PostMapping;
    import org.springframework.web.bind.annotation.RequestBody;
    import org.springframework.web.bind.annotation.RestController;

    @RestController
    public class OrderController {
        
        private final OrderProducerService orderProducerService;
    
        public OrderController(OrderProducerService orderProducerService) {
            this.orderProducerService = orderProducerService;
        }

        @PostMapping("/orders")
        public String placeOrder(@RequestBody Order order) {
            orderProducerService.sendOrder(order);
            return "Pedido enviado para processamento.";
        }
    }
    ```

    * Este controller expõe um endpoint POST em `/orders` que recebe um objeto `Order` no corpo da requisição e o envia para a fila através do `OrderProducerService`.

#### 4.2 Criando o Serviço Consumidor

1.  **Crie um novo projeto Spring Boot:**
    * GroupId: `com.example`
    * ArtifactId: `consumer-service`
    * Adicione a dependência: `Spring Boot Starter ActiveMQ`.

2.  **Configure o ActiveMQ no `application.properties` (igual ao serviço produtor):**

    ```properties
    spring.activemq.broker-url=tcp://localhost:61616
    spring.activemq.user=admin
    spring.activemq.password=admin
    ```

3.  **Crie a mesma classe `Order` (ou coloque-a em um projeto compartilhado/biblioteca):**

    ```java
    package com.example.consumerservice.model;

    public class Order {
        private String orderId;
        private String customerId;
        private double amount;

        // Construtores, getters e setters
        public Order() {
        }

        public Order(String orderId, String customerId, double amount) {
            this.orderId = orderId;
            this.customerId = customerId;
            this.amount = amount;
        }

       //getters e setters
    }
    ```

4.  **Crie um serviço para consumir mensagens da fila:**

    ```java
    package com.example.consumerservice.service;

    import com.example.consumerservice.model.Order;
    import org.springframework.jms.annotation.JmsListener;
    import org.springframework.stereotype.Service;

    @Service
    public class OrderConsumerService {

        @JmsListener(destination = "order.queue")
        public void receiveOrder(Order order) {
            System.out.println("Pedido recebido: " + order);
            // Lógica para processar o pedido (ex: salvar no banco de dados, etc.)
        }
    }
    ```

    * `@JmsListener(destination = "order.queue")`: Esta anotação marca o método `receiveOrder` como um listener de mensagens na fila chamada `order.queue`. Sempre que uma nova mensagem chegar a essa fila, este método será invocado com a mensagem convertida para o tipo `Order`.

#### 4.3 Executando os Microsserviços

1.  Certifique-se de que o ActiveMQ esteja em execução.
2.  Execute as aplicações `producer-service` e `consumer-service` como aplicações Spring Boot (por exemplo, executando a classe principal com o método `main`).

#### 4.4 Testando a Comunicação

1.  Envie uma requisição POST para o endpoint do serviço produtor (`http://localhost:8080/orders` por padrão, dependendo da sua configuração) com um corpo JSON representando um objeto `Order`:

    ```json
    {
        "orderId": "123",
        "customerId": "user-456",
        "amount": 99.99
    }
    ```

    Você pode usar ferramentas como `curl`, Postman ou Insomnia para enviar a requisição.

2.  Verifique o console do serviço produtor. Você deverá ver uma mensagem indicando que o pedido foi enviado.

3.  Verifique o console do serviço consumidor. Você deverá ver uma mensagem indicando que o pedido foi recebido e o objeto `Order` com os dados enviados pelo produtor.

4.  Você também pode monitorar as filas no painel de administração do ActiveMQ em `http://localhost:8161/admin`. Você deverá ver a fila `order.queue` e as mensagens sendo enfileiradas e dequeued.

### 5. Considerações Adicionais e Para aprofundar os estudos

* **Serialização:** Por padrão, o `JmsTemplate` usa a serialização Java para converter objetos em mensagens. Para interoperabilidade com outras linguagens ou sistemas, considere usar formatos como JSON (utilizando `Jackson2JsonMessageConverter` configurado no `JmsTemplate`).
* **Tratamento de Erros:** Implemente mecanismos de tratamento de erros tanto no produtor (para falhas ao enviar) quanto no consumidor (para falhas ao processar). Isso pode envolver retries, dead-letter queues (DLQ) para mensagens que não puderam ser processadas, etc.
* **Configuração Avançada:** O Spring Boot oferece diversas opções de configuração para o ActiveMQ, como gerenciamento de conexão, listeners concorrentes, etc. Consulte a documentação do Spring Boot e do ActiveMQ para configurações mais avançadas.
* **Tópicos:** Para implementar a comunicação usando tópicos, você precisaria alterar o destino no `JmsTemplate.convertAndSend()` e na anotação `@JmsListener` para o nome do tópico. Vários consumidores inscritos nesse tópico receberiam a mesma mensagem.
* **Transações:** Para garantir a atomicidade entre o envio/recebimento de mensagens e outras operações (como persistir dados no banco), você pode usar o suporte a transações do JMS e do Spring.

### 6. Leitura e vídeos complementares

[Apache ActiveMQ — Principais diferenças entre Classic e Artemis](https://medium.com/@astrodust/apache-activemq-key-differences-on-classic-vs-artemis-a22efe9ea45a)

[ActiveMQ with Spring Boot](https://lomana.medium.com/activemq-with-spring-boot-613fa39af860)

[Microsserviços na prática: mensageria com RabbitMQ](https://cursos.alura.com.br/course/microsservicos-pratica-mensageria-rabbitmq)

[Spring Boot-Reference-Messaging-JMS](https://docs.spring.io/spring-boot/reference/messaging/jms.html)

[Spring Cloud OpenFeign](https://spring.io/projects/spring-cloud-openfeign)
