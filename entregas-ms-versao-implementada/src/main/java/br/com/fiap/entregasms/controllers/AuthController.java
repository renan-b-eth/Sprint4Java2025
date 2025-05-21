package br.com.fiap.entregasms.controllers;


import br.com.fiap.entregasms.models.Dentista;
import br.com.fiap.entregasms.services.DentistaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    @Autowired
    private DentistaService dentistaService;

    @GetMapping("/login")
    public String showLoginPage() {
        return "login"; // Retorna o nome do arquivo Thymeleaf (login.html)
    }

    @GetMapping("/register")
    public String showRegisterPage(Model model) {
        model.addAttribute("dentista", new Dentista()); // Adiciona um novo objeto Dentista para o formulário
        return "register"; // Retorna o nome do arquivo Thymeleaf (register.html)
    }

    @PostMapping("/register")
    public String registerDentista(@Valid @ModelAttribute("dentista") Dentista dentista, BindingResult result, Model model) {
        if (result.hasErrors()) {
            // Se houver erros de validação, retorna para a página de registro com os erros
            return "register";
        }

        try {
            dentistaService.cadastrarDentista(dentista);
            model.addAttribute("successMessage", "Cadastro realizado com sucesso! Faça o login.");
            return "login"; // Redireciona para a página de login após o cadastro
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "register"; // Retorna para a página de registro com a mensagem de erro
        }
    }
}