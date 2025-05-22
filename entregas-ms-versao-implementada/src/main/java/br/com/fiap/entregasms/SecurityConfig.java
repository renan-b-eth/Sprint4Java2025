// src/main/java/br/com/fiap/entregasms/config/SecurityConfig.java
package br.com.fiap.entregasms;

import br.com.fiap.entregasms.services.UsuarioService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // REMOVA ESTA LINHA: @Autowired private UsuarioService usuarioService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // O método configureGlobal deve receber o UsuarioService como parâmetro
    // e o AuthenticationManagerBuilder é passado pelo Spring automaticamente
    // em um método que tem essa assinatura com @Autowired.
    // Assim, removemos a injeção de campo.
    // É importante que este método esteja aqui para configurar o AuthenticationManager.
    // No Spring Boot 3, não é mais necessário o @Autowired neste método, mas ele ainda pode ser útil
    // se você tiver mais de um UserDetailsService e precisar especificar qual usar.
    // No entanto, para evitar confusão de dependências, vamos refatorar configureGlobal
    // para ser acionado por um método do SecurityFilterChain.

    // A maneira mais limpa no Spring Security 6+ é fazer a configuração do UserDetailsService
    // diretamente no AuthenticationManagerBuilder que é injetado no SecurityFilterChain.
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, UsuarioService usuarioService) throws Exception {
        // Configura o AuthenticationManagerBuilder diretamente aqui
        // ou você pode ter um método auxiliar se a lógica for mais complexa
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);

        authenticationManagerBuilder
                .userDetailsService(usuarioService)
                .passwordEncoder(passwordEncoder()); // passwordEncoder() é um bean do SecurityConfig

        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(
                                "/registro", "/registro/**",
                                "/login", "/login/**",
                                "/css/**", "/js/**", "/images/**",
                                "/h2-console/**"
                        ).permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/dashboard", true)
                        .failureUrl("/login?error")
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                )
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers("/h2-console/**")
                )
                .headers(headers -> headers
                        .frameOptions(frameOptions -> frameOptions.sameOrigin())
                );
        return http.build();
    }


}