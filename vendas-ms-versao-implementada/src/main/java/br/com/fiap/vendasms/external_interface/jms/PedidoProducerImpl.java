package br.com.fiap.vendasms.external_interface.jms;

import br.com.fiap.vendasms.dtos.PedidoDto;
import br.com.fiap.vendasms.external_interface.cep.CepApi;
import br.com.fiap.vendasms.model.Pedido;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
final class PedidoProducerImpl implements PedidoProducer {

    private final JmsTemplate jmsTemplate;

    private final CepApi cepApi;

    private final ObjectMapper objectMapper;

    private static final String PEDIDO_QUEUE = "pedido.queue";

    PedidoProducerImpl(JmsTemplate jmsTemplate, CepApi cepApi, ObjectMapper objectMapper) {
        this.jmsTemplate = jmsTemplate;
        this.cepApi = cepApi;
        this.objectMapper = objectMapper;
    }

    @Override
    public void sendPedido(Pedido pedido) throws JsonProcessingException {
        final PedidoDto pedidoDTO = PedidoDto.from(pedido);
        pedidoDTO.getCliente().setCepDetails(this.cepApi.get(pedido.getCliente().getCep()));
        final String pedidoJson = this.objectMapper.writeValueAsString(pedidoDTO);
        this.jmsTemplate.convertAndSend(PEDIDO_QUEUE, pedidoJson);
    }
}
