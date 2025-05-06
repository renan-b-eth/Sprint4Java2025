package br.com.fiap.vendasms.service;

import br.com.fiap.vendasms.external_interface.jms.PedidoProducer;
import br.com.fiap.vendasms.model.Pedido;
import br.com.fiap.vendasms.repositories.PedidoRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class PedidoServiceImpl implements PedidoService {

    private final PedidoRepository repository;
    private final PedidoProducer pedidoProducer;

    public PedidoServiceImpl(PedidoRepository repository, PedidoProducer pedidoProducer) {
        this.repository = repository;
        this.pedidoProducer = pedidoProducer;
    }

    @Override
    public List<Pedido> findByClienteCpf(String cpf) {
        return this.repository.findByCliente_Cpf(cpf);
    }

    @Override
    @Transactional
    public void save(Pedido pedido) throws JsonProcessingException {
        this.repository.save(pedido);
        this.pedidoProducer.sendPedido(pedido);
    }
}
