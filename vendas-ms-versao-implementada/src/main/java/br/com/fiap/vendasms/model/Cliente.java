package br.com.fiap.vendasms.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Cliente {

    @Id
    private String cpf;

    private String nome, cep, numero, complemento, telefone;

    public Cliente() {
        super();
    }

    public Cliente(String cpf, String nome, String cep, String numero, String complemento, String telefone) {
        this.cpf = cpf;
        this.nome = nome;
        this.cep = cep;
        this.complemento = complemento;
        this.telefone = telefone;
        this.numero = numero;
    }

    public Cliente(String cpf) {
        this.cpf = cpf;
    }

    public String getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public String getCep() {
        return cep;
    }

    public String getComplemento() {
        return complemento;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getNumero() {
        return numero;
    }
}
