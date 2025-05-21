package br.com.fiap.entregasms; // ou o seu pacote de configuração, ex: br.com.fiap.entregasms.config

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService; // Importe esta interface
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // 1. Define o PasswordEncoder como um Bean
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 2. Define o SecurityFilterChain para configurar as regras de segurança HTTP
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        // Permite acesso público a estas URLs
                        .requestMatchers("/login", "/register", "/css/**", "/h2-console/**").permitAll()
                        // Todas as outras requisições exigem autenticação
                        .anyRequest().authenticated()
                )
                // Configuração de formulário de login
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/home", true) // Redireciona para /home após login bem-sucedido
                        .permitAll() // Permite acesso à página de login
                )
                // Configuração de logout
                .logout(logout -> logout
                        .logoutUrl("/logout") // URL para processar o logout
                        .logoutSuccessUrl("/login?logout") // Redireciona após logout bem-sucedido
                        .permitAll() // Permite acesso ao endpoint de logout
                )
                // Desabilita CSRF para o console H2 (se usado)
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers("/h2-console/**")
                )
                // Permite frames para o console H2
                .headers(headers -> headers
                        .frameOptions(frameOptions -> frameOptions.sameOrigin())
                );
        return http.build();
    }

    // 3. Define o AuthenticationManager como um Bean (NOVO!)
    // Este bean será injetado em seu DentistaService indiretamente.
    // O Spring Boot detecta automaticamente o UserDetailsService e o PasswordEncoder.
    @Bean
    public AuthenticationManager authenticationManager(
            UserDetailsService userDetailsService,
            PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        return new ProviderManager(authenticationProvider);
    }
}