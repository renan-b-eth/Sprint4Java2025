package com.fiap.odontoprev.odontoprev.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioUpdateRequest {

    @NotEmpty
    private String nome;

    @NotEmpty
    private String senha;






}
