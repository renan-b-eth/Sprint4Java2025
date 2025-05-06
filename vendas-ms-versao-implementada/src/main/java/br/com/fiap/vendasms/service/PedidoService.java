package br.com.fiap.vendasms.service;

import br.com.fiap.vendasms.model.Pedido;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface PedidoService {
    List<Pedido> findByClienteCpf(String cpf);

    void save(Pedido pedido) throws JsonProcessingException;
}
