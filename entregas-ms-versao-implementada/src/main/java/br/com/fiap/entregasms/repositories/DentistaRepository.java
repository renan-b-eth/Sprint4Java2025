package br.com.fiap.entregasms.repositories;


import br.com.fiap.entregasms.models.Dentista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DentistaRepository extends JpaRepository<Dentista, Long> {

}
