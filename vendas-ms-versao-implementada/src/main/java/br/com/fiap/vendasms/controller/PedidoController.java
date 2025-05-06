package br.com.fiap.vendasms.controller;

import br.com.fiap.vendasms.dtos.ClienteDto;
import br.com.fiap.vendasms.dtos.PedidoDto;
import br.com.fiap.vendasms.external_interface.cep.CepApi;
import br.com.fiap.vendasms.model.Pedido;
import br.com.fiap.vendasms.service.ClienteService;
import br.com.fiap.vendasms.service.PedidoService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/pedidos")
public class PedidoController extends CommonController {

    private final PedidoService pedidoService;
    private final ClienteService clienteService;
    private final CepApi cepApi;

    public PedidoController(PedidoService pedidoService, ClienteService clienteService, CepApi cepApi) {
        this.pedidoService = pedidoService;
        this.clienteService = clienteService;
        this.cepApi = cepApi;
    }

    @GetMapping
    public String pedidos() {
        return "pedidos";
    }


    @GetMapping("/detalhe/{cpf}")
    public String detalhePedidos(Model model, @PathVariable("cpf") String cpf) {
        ClienteDto cliente = ClienteDto.from(this.clienteService.findById(cpf));
        if (cliente.getNome() == null) {
            model.addAttribute("cliente",cliente);
            return "detalhe-cliente";
        }

        cliente.setCepDetails(this.cepApi.get(cliente.getCep()));
        model.addAttribute("cliente", cliente);
        model.addAttribute("pedidos", PedidoDto.from(this.pedidoService.findByClienteCpf(cpf)));

        return "detalhe-pedidos";
    }


    @PostMapping("/novo")
    public String novoPedido(Model model, String cpf) {
        ClienteDto cliente = ClienteDto.from(this.clienteService.findById(cpf));

        cliente.setCepDetails(this.cepApi.get(cliente.getCep()));
        model.addAttribute("cliente", cliente);

        model.addAttribute("pedido", new PedidoDto(cliente));
        return "novo-pedido";
    }

    @PostMapping("/novo/salvar")
    public String salvarNovoPedido(PedidoDto pedido) {
        final Pedido pedidoToSave = pedido.toEntity();
        pedidoToSave.setCliente(this.clienteService.findById(pedido.getCliente().getCpf()));
        try {
            this.pedidoService.save(pedidoToSave);
            return "redirect:/pedidos/detalhe/"+pedido.getCliente().getCpf();
        } catch (JsonProcessingException e) {
            return "redirect:/pedidos/novo";
        }

    }
}
