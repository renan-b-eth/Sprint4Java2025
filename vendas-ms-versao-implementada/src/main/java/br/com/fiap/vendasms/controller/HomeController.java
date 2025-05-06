package br.com.fiap.vendasms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/")
public class HomeController extends CommonController {

    @GetMapping
    public String home() {
        return "index";
    }

}
