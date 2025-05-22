// src/main/java/br/com/fiap/entregasms/service/DentistaService.java
package br.com.fiap.entregasms.services;

import br.com.fiap.entregasms.models.Dentista;
import br.com.fiap.entregasms.repositories.DentistaRepository;
import org.springframework.beans.factory.annotation.Autowired; // Pode remover se usar apenas construtor
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DentistaService {

    // Certifique-se de que a injeção é APENAS do DentistaRepository
    private final DentistaRepository dentistaRepository;

    // Construtor para injeção de dependência. É a forma mais limpa.
    public DentistaService(DentistaRepository dentistaRepository) {
        this.dentistaRepository = dentistaRepository;
    }

    // Métodos de negócio do DentistaService
    public List<Dentista> findAll() {
        return dentistaRepository.findAll();
    }

    public Optional<Dentista> findById(Long id) {
        return dentistaRepository.findById(id);
    }

    public Dentista save(Dentista dentista) {
        return dentistaRepository.save(dentista);
    }

    public void deleteById(Long id) {
        dentistaRepository.deleteById(id);
    }
}