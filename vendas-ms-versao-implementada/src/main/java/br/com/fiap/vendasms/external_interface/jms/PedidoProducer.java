package br.com.fiap.vendasms.external_interface.jms;

import br.com.fiap.vendasms.model.Pedido;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface PedidoProducer {

    void sendPedido(Pedido pedido) throws JsonProcessingException;
}
