package br.com.fiap.vendasms.dtos;

import br.com.fiap.vendasms.model.Pedido;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class PedidoDto {

    private UUID id;

    private ClienteDto cliente;

    private String descricao, status;

    public PedidoDto() {
        super();
    }

    public PedidoDto(UUID id, ClienteDto cliente, String descricao, String status) {
        this.id = id;
        this.cliente = cliente;
        this.descricao = descricao;
        this.status = status;
    }

    public PedidoDto(ClienteDto cliente) {
        this.cliente = cliente;
    }

    public static PedidoDto from(Pedido p) {
        return new PedidoDto(
                p.getId(),
                ClienteDto.from(p.getCliente()),
                p.getDescricao(),
                p.getStatus().name()
        );
    }

    public static List<PedidoDto> from(List<Pedido> pedidos) {
        return pedidos
                .stream()
                .map(PedidoDto::from)
                .collect(Collectors.toList());
    }

    public Pedido toEntity() {
        return new Pedido(this.getId(),
                this.getCliente().toEntity(),
                this.getStatus() == null ? Pedido.Status.PENDENTE_ENVIO : Pedido.Status.valueOf(this.getStatus()),
                this.getDescricao());
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public ClienteDto getCliente() {
        return cliente;
    }

    public void setCliente(ClienteDto cliente) {
        this.cliente = cliente;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
