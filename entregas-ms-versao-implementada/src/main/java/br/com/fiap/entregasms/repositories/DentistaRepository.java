package br.com.fiap.entregasms.repositories;


import br.com.fiap.entregasms.models.Dentista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DentistaRepository extends JpaRepository<Dentista, Long> {
    Optional<Dentista> findByEmail(String email);
    boolean existsByEmail(String email);
    boolean existsByCro(String cro);
}
