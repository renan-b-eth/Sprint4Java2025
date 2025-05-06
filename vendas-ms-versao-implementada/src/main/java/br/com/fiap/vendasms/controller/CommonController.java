package br.com.fiap.vendasms.controller;

import br.com.fiap.vendasms.utils.GithubUserUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.ModelAttribute;

class CommonController {

    @ModelAttribute
    private void preProcess(HttpServletRequest request, OAuth2AuthenticationToken authentication) {
        this.loadSession(request, authentication);
    }

    private void loadSession(HttpServletRequest request, OAuth2AuthenticationToken authentication) {
        request.getSession().setAttribute("username", GithubUserUtils.getUsername(authentication));
        request.getSession().setAttribute("urlAvatar", GithubUserUtils.getAvatar(authentication));
    }

}
