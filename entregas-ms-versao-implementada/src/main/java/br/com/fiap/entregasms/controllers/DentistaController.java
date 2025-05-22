// src/main/java/br/com/fiap/entregasms/controller/DentistaController.java
package br.com.fiap.entregasms.controllers;

import br.com.fiap.entregasms.models.Dentista;
import br.com.fiap.entregasms.repositories.DentistaRepository;
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

import java.util.Collections;
import java.util.List;
import java.util.Locale; // Importar Locale
import java.util.stream.Collectors;

@Controller
public class DentistaController {

    @Autowired
    private DentistaRepository dentistaRepository;

    @Autowired
    private MessageSource messageSource; // Injetar MessageSource

    @GetMapping("/dashboard")
    public String showDashboard(Model model) {
        List<Dentista> todosDentistas = dentistaRepository.findAll();

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
                               Model model,
                               Locale locale) { // Injetar Locale
        if (result.hasErrors()) {
            List<Dentista> todosDentistas = dentistaRepository.findAll();
            Collections.shuffle(todosDentistas);
            List<Dentista> dentistasAleatorios = todosDentistas.stream().limit(5).collect(Collectors.toList());
            model.addAttribute("dentistas", dentistasAleatorios);

            redirectAttributes.addFlashAttribute("errorMessage", messageSource.getMessage("dentist.error.add", null, locale));
            return "dashboard";
        }

        try {
            dentistaRepository.save(dentista);
            redirectAttributes.addFlashAttribute("successMessage", messageSource.getMessage("dentist.success.add", null, locale));
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", messageSource.getMessage("dentist.error.add", new Object[]{e.getMessage()}, locale));
        }

        return "redirect:/dashboard";
    }
}