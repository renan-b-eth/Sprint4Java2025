package br.com.fiap.roardemo.controller;

import br.com.fiap.roardemo.service.RoarService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RoarController {

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("roars", RoarService.getRoars());
        return "index";
    }

    @PostMapping("/roar")
    public String roar(@RequestParam("content") String content) {
        RoarService.addRoar(content,"Usuario Teste");
        return "redirect:/";
    }

    @PostMapping("/like")
    public String like(@RequestParam("roarId") Long tweetId) {
        RoarService.likeRoar(tweetId);
        return "redirect:/";
    }

}
