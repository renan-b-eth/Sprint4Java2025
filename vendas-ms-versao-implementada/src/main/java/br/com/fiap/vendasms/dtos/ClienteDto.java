package br.com.fiap.vendasms.dtos;

import br.com.fiap.vendasms.external_interface.cep.CepDetails;
import br.com.fiap.vendasms.model.Cliente;

public class ClienteDto {

    private String cpf, nome, cep, numero, logradouro, bairro, localidade, estado, complemento, telefone;

    public ClienteDto() {
        super();
    }

    public ClienteDto(String cpf, String nome, String cep, String numero, String complemento, String telefone) {
        this.cpf = cpf;
        this.nome = nome;
        this.cep = cep;
        this.numero = numero;
        this.complemento = complemento;
        this.telefone = telefone;
    }

    public static ClienteDto from(Cliente cliente) {
        return new ClienteDto(
                cliente.getCpf(),
                cliente.getNome(),
                cliente.getCep(),
                cliente.getNumero(),
                cliente.getComplemento(),
                cliente.getTelefone()
        );
    }

    public Cliente toEntity() {
        return new Cliente(
                this.getCpf(),
                this.getNome(),
                this.getCep(),
                this.getNumero(),
                this.getComplemento(),
                this.getTelefone()
        );
    }

    public void setCepDetails(CepDetails cep){
        this.logradouro = cep.getLogradouro();
        this.bairro = cep.getBairro();
        this.localidade = cep.getLocalidade();
        this.estado = cep.getEstado();
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public String getBairro() {
        return bairro;
    }

    public String getLocalidade() {
        return localidade;
    }

    public String getEstado() {
        return estado;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

}
