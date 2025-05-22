package br.com.fiap.entregasms.dtos;


import br.com.fiap.entregasms.models.Usuario;
import br.com.fiap.entregasms.request.UsuarioCreateRequest;
import br.com.fiap.entregasms.request.UsuarioLoginRequest;
import br.com.fiap.entregasms.request.UsuarioUpdateRequest;
import org.springframework.stereotype.Component;

@Component
public class UsuarioDtoMapper {

    public Usuario converterUsuarioDto(UsuarioCreateRequest usuarioCreateRequest) {
        Usuario usuario = new Usuario();

        usuario.setNome(usuarioCreateRequest.getNome());
        /*usuario.setCpf(usuarioCreateRequest.getCpf());
        usuario.setDataNascimento(usuarioCreateRequest.getDataNascimento());
        usuario.setSenha(usuarioCreateRequest.getSenha());*/
        return usuario;
    }

    public Usuario converterUsuarioUpdateDto(UsuarioUpdateRequest usuarioUpdateRequest) {
        Usuario usuario = new Usuario();
        usuario.setNome(usuarioUpdateRequest.getNome());
        /*usuario.setSenha(usuarioUpdateRequest.getSenha());*/
        return usuario;
    }


    public Usuario converterUsuarioLoginDto(UsuarioLoginRequest usuarioLoginRequest) {
        Usuario usuario = new Usuario();
        /*usuario.setCpf(usuarioLoginRequest.getCpf());
        usuario.setSenha(usuarioLoginRequest.getSenha());*/
        return usuario;
    }
}

