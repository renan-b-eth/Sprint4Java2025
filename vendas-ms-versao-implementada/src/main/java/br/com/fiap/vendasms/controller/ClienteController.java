package br.com.fiap.vendasms.controller;

import br.com.fiap.vendasms.dtos.ClienteDto;
import br.com.fiap.vendasms.service.ClienteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/clientes")
public class ClienteController extends CommonController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public String clientes(Model model) {
        model.addAttribute("cpf", new String());
        return "cliente";
    }

    @PostMapping("/detalhe")
    public String detalhe(Model model, String cpf) {
        model.addAttribute("cliente", ClienteDto.from(this.clienteService.findById(cpf)));
        return "detalhe-cliente";
    }

    @PostMapping("/save")
    public String save(ClienteDto cliente) {
        this.clienteService.saveOrUpdate(cliente.toEntity());
        return "redirect:/pedidos/detalhe/"+ cliente.getCpf();
    }
}
