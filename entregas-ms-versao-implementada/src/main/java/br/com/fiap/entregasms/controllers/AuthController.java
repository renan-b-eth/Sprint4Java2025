// src/main/java/br/com/fiap/entregasms/controller/AuthController.java
package br.com.fiap.entregasms.controllers;

import br.com.fiap.entregasms.models.Usuario;
import br.com.fiap.entregasms.services.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource; // Importar MessageSource
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Locale; // Importar Locale

@Controller
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private MessageSource messageSource; // Injetar MessageSource

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @GetMapping("/registro")
    public String showRegistrationForm(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "registro";
    }

    @PostMapping("/registro")
    public String registerUser(@Valid @ModelAttribute("usuario") Usuario usuario,
                               BindingResult result,
                               RedirectAttributes redirectAttributes,
                               Locale locale) { // Injetar Locale para obter o idioma atual
        if (result.hasErrors()) {
            return "registro";
        }

        try {
            usuarioService.registrarNovoUsuario(usuario);
            redirectAttributes.addFlashAttribute("successMessage", messageSource.getMessage("register.success", null, locale));
            return "redirect:/login";
        } catch (IllegalArgumentException e) {
            result.rejectValue("email", "error.usuario", messageSource.getMessage("register.error.email.exists", null, locale));
            return "registro";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", messageSource.getMessage("register.error.generic", new Object[]{e.getMessage()}, locale));
            return "redirect:/registro";
        }
    }
}