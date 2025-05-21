package br.com.fiap.entregasms.models;

import lombok.Data;

import java.util.UUID;

@Data
public class Usuario {
    private String idUsuario;
    private String nome;
    private String cpf;
    private String dataNascimento;
    private String senha;

    public Usuario() {
        this.idUsuario = UUID.randomUUID().toString();
    }


}
