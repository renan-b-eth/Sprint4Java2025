// src/main/java/br/com/fiap/entregasms/repository/UsuarioRepository.java
package br.com.fiap.entregasms.repositories;

import br.com.fiap.entregasms.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);
}