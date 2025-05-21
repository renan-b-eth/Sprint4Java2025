package com.fiap.odontoprev.odontoprev.controller;


import com.fiap.odontoprev.odontoprev.mapper.UsuarioDtoMapper;
import com.fiap.odontoprev.odontoprev.model.Usuario;
import com.fiap.odontoprev.odontoprev.request.UsuarioCreateRequest;
import com.fiap.odontoprev.odontoprev.request.UsuarioLoginRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.fiap.odontoprev.odontoprev.service.UsuarioService;

@Slf4j
@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioDtoMapper usuarioDtoMapper;

    private static final String ERRO_CADASTRO_USUARIO = "Erro ao cadastrar usuário";

    private static final String ERRO_CREDENCIAIS = "Credenciais inválidas";



    @GetMapping("/")
    public String redirectToCadastro() {

        log.info("Iniciando o controller de usuário");

        return "redirect:/usuario/cadastro";
    }



    @GetMapping("/cadastro")
    public ModelAndView telaCadastro() {
        ModelAndView modelAndView = new ModelAndView("cadastro");
        modelAndView.addObject("usuarioDto", new UsuarioCreateRequest());
        return modelAndView;
    }


    @PostMapping("/cadastrar")
    public ModelAndView cadastrarUsuario(@ModelAttribute UsuarioCreateRequest usuarioDto) {
        ModelAndView modelAndView = new ModelAndView();

        try {
            Usuario usuario = usuarioDtoMapper.converterUsuarioDto(usuarioDto);

            usuarioService.cadastrarUsuario(usuario);

            log.info("Usuário cadastrado com sucesso: {}", usuario.getCpf());

            modelAndView.setViewName("redirect:/usuario/login");
        } catch (Exception e) {

            log.error("Erro ao cadastrar usuário: {}", ERRO_CADASTRO_USUARIO);

            modelAndView.addObject("erro", ERRO_CADASTRO_USUARIO);

            modelAndView.setViewName("cadastro");
        }

        return modelAndView;
    }


    @GetMapping("/login")
    public ModelAndView login(HttpSession session) {

        if (session.getAttribute("token") != null) {
            return new ModelAndView("redirect:/usuario/home");
        }

        ModelAndView mv = new ModelAndView("login");

        mv.addObject("usuarioLoginRequest", new UsuarioLoginRequest());

        return mv;
    }




    @PostMapping("/login")
    public ModelAndView login(@ModelAttribute UsuarioLoginRequest usuarioLoginRequest, HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();

        try {
            String token = usuarioService.validarLogin(usuarioLoginRequest.getCpf(), usuarioLoginRequest.getSenha());

            session.setAttribute("token", token);

            log.info("Usuário logado com sucesso: {}", usuarioLoginRequest.getCpf());

            modelAndView.setViewName("redirect:/usuario/home");
        } catch (Exception e) {
            log.error("Erro ao logar usuário: {}", ERRO_CREDENCIAIS);

            modelAndView.addObject("erro", ERRO_CREDENCIAIS);

            modelAndView.setViewName("login");
        }

        return modelAndView;
    }


    @GetMapping("/home")
    public ModelAndView home(HttpSession session) {

        if (session.getAttribute("token") == null) {

            return new ModelAndView("redirect:/usuario/login");

        }
        ModelAndView mv = new ModelAndView("home");

        return mv;
    }

    @GetMapping("/consultas")
    public ModelAndView telaConsultas() {
        ModelAndView mv = new ModelAndView("consultas");
        return mv;
    }


    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();

        return "redirect:/usuario/login";
    }


}
