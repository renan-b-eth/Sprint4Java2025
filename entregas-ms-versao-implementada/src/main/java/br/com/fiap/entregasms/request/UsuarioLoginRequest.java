package com.fiap.odontoprev.odontoprev.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;


@Data
public class UsuarioLoginRequest {

    @NotBlank
    private String cpf;

    @NotBlank
    private String senha;


}

