package br.com.fiap.vendasms.service;

import br.com.fiap.vendasms.model.Cliente;

public interface ClienteService {
    Cliente findById(String cpf);

    void saveOrUpdate(Cliente cliente);
}
