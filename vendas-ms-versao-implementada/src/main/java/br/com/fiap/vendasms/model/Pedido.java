package br.com.fiap.vendasms.model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @Enumerated(EnumType.STRING)
    private Status status;

    private String descricao;

    public Pedido() {
        super();
    }

    public Pedido(UUID id, Cliente cliente, Status status, String descricao) {
        this.id = id;
        this.cliente = cliente;
        this.status = status;
        this.descricao = descricao;
    }

    public UUID getId() {
        return id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Status getStatus() {
        return status;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public enum Status {
        PENDENTE_ENVIO,
        ENVIO_EM_PROCESSAMENTO,
        FINALIZADO
    }
}
