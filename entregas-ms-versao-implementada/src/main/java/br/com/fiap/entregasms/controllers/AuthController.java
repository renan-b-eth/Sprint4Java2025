// src/main/java/br/com/fiap/entregasms/controller/AuthController.java
package br.com.fiap.entregasms.controllers;

import br.com.fiap.entregasms.models.Usuario;
import br.com.fiap.entregasms.services.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/login")
    public String showLoginForm() {
        return "login"; // Nome do arquivo Thymeleaf
    }

    @GetMapping("/registro")
    public String showRegistrationForm(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "registro"; // Nome do arquivo Thymeleaf
    }

    @PostMapping("/registro")
    public String registerUser(@Valid @ModelAttribute("usuario") Usuario usuario,
                               BindingResult result,
                               RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            // Se houver erros de validação (ex: nome em branco), retorna para o formulário
            return "registro";
        }

        try {
            usuarioService.registrarNovoUsuario(usuario);
            redirectAttributes.addFlashAttribute("successMessage", "Usuário registrado com sucesso! Faça login.");
            return "redirect:/login"; // Redireciona para a página de login
        } catch (IllegalArgumentException e) {
            // Captura o erro de email duplicado do serviço
            result.rejectValue("email", "error.usuario", e.getMessage());
            return "registro"; // Retorna para o formulário com o erro no campo email
        } catch (Exception e) {
            // Outros erros inesperados
            redirectAttributes.addFlashAttribute("errorMessage", "Erro ao registrar: " + e.getMessage());
            return "redirect:/registro";
        }
    }
}