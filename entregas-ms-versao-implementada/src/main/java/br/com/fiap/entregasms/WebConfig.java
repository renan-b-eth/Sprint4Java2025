// src/main/java/br/com/fiap/entregasms/config/WebConfig.java
package br.com.fiap.entregasms;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver slr = new SessionLocaleResolver(); // Mude para SessionLocaleResolver
        slr.setDefaultLocale(new Locale("pt", "BR")); // Idioma padrão: Português do Brasil
        return slr;
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        lci.setParamName("lang"); // Nome do parâmetro da URL para trocar de idioma (ex: ?lang=en)
        return lci;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // ESSENCIAL: Adicionar o interceptor para que ele possa agir na requisição
        registry.addInterceptor(localeChangeInterceptor());
    }

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:/i18n/messages"); // Caminho para os arquivos .properties
        messageSource.setDefaultEncoding("UTF-8"); // Codificação dos arquivos
        messageSource.setCacheSeconds(10); // Recarrega os arquivos a cada 10 segundos em dev
        return messageSource;
    }
}