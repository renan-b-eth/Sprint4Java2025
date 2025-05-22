// src/main/java/br/com/fiap/entregasms/controller/DentistaController.java
package br.com.fiap.entregasms.controllers;

import br.com.fiap.entregasms.models.Dentista;
import br.com.fiap.entregasms.repositories.DentistaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class DentistaController {

    @Autowired
    private DentistaRepository dentistaRepository; // Ou private DentistaService dentistaService;

    // Se você estiver usando o DentistaService:
    // private final DentistaService dentistaService;
    // public DentistaController(DentistaService dentistaService) {
    //     this.dentistaService = dentistaService;
    // }

    @GetMapping("/dashboard")
    public String showDashboard(Model model) {
        List<Dentista> todosDentistas = dentistaRepository.findAll(); // Ou dentistaService.findAll();

        Collections.shuffle(todosDentistas);
        List<Dentista> dentistasAleatorios = todosDentistas.stream()
                .limit(5)
                .collect(Collectors.toList());

        model.addAttribute("dentistas", dentistasAleatorios);
        model.addAttribute("novoDentista", new Dentista());
        return "dashboard";
    }

    @PostMapping("/dentistas")
    public String saveDentista(@Valid @ModelAttribute("novoDentista") Dentista dentista,
                               BindingResult result,
                               RedirectAttributes redirectAttributes,
                               Model model) {
        if (result.hasErrors()) {
            List<Dentista> todosDentistas = dentistaRepository.findAll(); // Ou dentistaService.findAll();
            Collections.shuffle(todosDentistas);
            List<Dentista> dentistasAleatorios = todosDentistas.stream().limit(5).collect(Collectors.toList());
            model.addAttribute("dentistas", dentistasAleatorios);
            redirectAttributes.addFlashAttribute("errorMessage", "Erro ao cadastrar dentista. Verifique os campos.");
            return "dashboard";
        }

        try {
            dentistaRepository.save(dentista); // Ou dentistaService.save(dentista);
            redirectAttributes.addFlashAttribute("successMessage", "Dentista cadastrado com sucesso!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Erro ao cadastrar dentista: " + e.getMessage());
        }

        return "redirect:/dashboard";
    }
}