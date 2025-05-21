package br.com.fiap.entregasms.services;

import br.com.fiap.entregasms.models.Usuario;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
final class UsuarioServiceImpl implements UsuarioService {

    @Override
    public void cadastrarUsuario(Usuario usuario) {

    }

    @Override
    public List<Usuario> listarUsuario() {
        return List.of();
    }

    @Override
    public Optional<Usuario> atualizarUsuario(String id, Usuario usuario) {
        return Optional.empty();
    }

    @Override
    public boolean excluirUsuairo(String id) {
        return false;
    }

    @Override
    public Usuario buscarUsuario(String id) {
        return null;
    }

    @Override
    public String validarLogin(String cpf, String senhaDigitada) {
        return "";
    }
}
