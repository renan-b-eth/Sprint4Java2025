package br.com.fiap.vendasms.service;

import br.com.fiap.vendasms.model.Cliente;
import br.com.fiap.vendasms.repositories.ClienteRepository;
import org.springframework.stereotype.Service;

@Service
final class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository repository;

    ClienteServiceImpl(ClienteRepository repository) {
        this.repository = repository;
    }

    @Override
    public Cliente findById(String cpf) {
        return this.repository.findById(cpf).orElse(new Cliente(cpf));
    }

    @Override
    public void saveOrUpdate(Cliente cliente) {
        this.repository.save(cliente);
    }
}
