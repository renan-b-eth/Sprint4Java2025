package br.com.fiap.vendasms.repositories;

import br.com.fiap.vendasms.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, String> {
}