package br.com.fiap.entregasms.services; // Ou seu pacote correto

import br.com.fiap.entregasms.models.Dentista; // Sua classe Dentista
import br.com.fiap.entregasms.repositories.DentistaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class DentistaService implements UserDetailsService {

    private final DentistaRepository dentistaRepository;
    private final PasswordEncoder passwordEncoder; // Mantenha isso!

    // Injeção de dependência via construtor
    public DentistaService(DentistaRepository dentistaRepository, PasswordEncoder passwordEncoder) {
        this.dentistaRepository = dentistaRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return dentistaRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + email));
    }

    public Dentista cadastrarDentista(Dentista dentista) {
        // Usa o passwordEncoder injetado
        dentista.setSenha(passwordEncoder.encode(dentista.getSenha()));
        return dentistaRepository.save(dentista);
    }
}