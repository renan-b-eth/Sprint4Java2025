// src/main/java/br/com/fiap/entregasms/service/UsuarioService.java
package br.com.fiap.entregasms.services;

import br.com.fiap.entregasms.models.Usuario;
import br.com.fiap.entregasms.repositories.UsuarioRepository;
import org.springframework.context.annotation.Lazy; // Adicione este import
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder; // Agora é final e injetado via construtor

    // @Autowired já não é necessário no construtor se houver apenas um construtor
    public UsuarioService(UsuarioRepository usuarioRepository, @Lazy PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Usuario registrarNovoUsuario(Usuario usuario) {
        if (usuarioRepository.findByEmail(usuario.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email já cadastrado.");
        }
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        return usuarioRepository.save(usuario);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Usuario> usuario = usuarioRepository.findByEmail(email);
        if (usuario.isEmpty()) {
            throw new UsernameNotFoundException("Usuário não encontrado: " + email);
        }
        return usuario.get();
    }
}