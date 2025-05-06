package br.com.fiap.entregasms.external_interface.jms;

import br.com.fiap.entregasms.models.Entrega;
import br.com.fiap.entregasms.services.EntregaService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
class PedidoEntregaConsumer {

    private final EntregaService entregaService;
    private final ObjectMapper objectMapper;

    PedidoEntregaConsumer(EntregaService entregaService, ObjectMapper objectMapper) {
        this.entregaService = entregaService;
        this.objectMapper = objectMapper;
    }


    @Transactional
    @JmsListener(destination = "pedido.queue")
    public void consume(String message) throws JsonProcessingException {
        // transformar string em entrega
        final MessageInput input = this.objectMapper.readValue(message,MessageInput.class);
        // persistir entrega
        this.entregaService.save(new Entrega(input.getId(),input.getCliente().getNome(),input.getCliente().getEnderecoCompleto()));

    }

    //inner com o objeto mensagem
    private static final class MessageInput {
        private UUID id;
        private ClienteMessageInput cliente;

        public UUID getId() {
            return id;
        }

        public void setId(UUID id) {
            this.id = id;
        }

        public ClienteMessageInput getCliente() {
            return cliente;
        }

        public void setCliente(ClienteMessageInput cliente) {
            this.cliente = cliente;
        }

        public static final class ClienteMessageInput {
            private String nome;
            private String cep, numero, logradouro, bairro, localidade, estado, complemento;

            public String getEnderecoCompleto() {
                return logradouro.concat(", ").concat(numero).concat("\n").concat(complemento).concat("\n").concat(bairro).concat(" - ").concat(localidade).concat(", ").concat(estado).concat("\nCEP: ").concat(cep);
            }

            public String getNome() {
                return nome;
            }

            public void setNome(String nome) {
                this.nome = nome;
            }

            public String getCep() {
                return cep;
            }

            public void setCep(String cep) {
                this.cep = cep;
            }

            public String getNumero() {
                return numero;
            }

            public void setNumero(String numero) {
                this.numero = numero;
            }

            public String getLogradouro() {
                return logradouro;
            }

            public void setLogradouro(String logradouro) {
                this.logradouro = logradouro;
            }

            public String getBairro() {
                return bairro;
            }

            public void setBairro(String bairro) {
                this.bairro = bairro;
            }

            public String getLocalidade() {
                return localidade;
            }

            public void setLocalidade(String localidade) {
                this.localidade = localidade;
            }

            public String getEstado() {
                return estado;
            }

            public void setEstado(String estado) {
                this.estado = estado;
            }

            public String getComplemento() {
                return complemento;
            }

            public void setComplemento(String complemento) {
                this.complemento = complemento;
            }
        }
    }


}
