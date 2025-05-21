package br.com.fiap.entregasms.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioCreateRequest {

    private String idUsuario;

    @NotNull
    private String dataNascimento;

    @NotBlank
    private String nome;


    @NotBlank(message = "CPF não pode estar vazio")
    @CPF(message = "CPF inválido")
    private String cpf;


    @NotBlank(message = "Senha não pode estar vazia")
    @Size(min = 8, message = "Senha deve ter no mínimo 8 caracteres")
    private String senha;


}

