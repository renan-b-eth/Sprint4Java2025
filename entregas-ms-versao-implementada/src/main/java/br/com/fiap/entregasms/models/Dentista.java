// src/main/java/br/com/fiap/entregasms/model/Dentista.java
package br.com.fiap.entregasms.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "dentistas") // Nome da tabela no banco de dados
public class Dentista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome é obrigatório")
    @Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres")
    private String nome;

    @NotBlank(message = "O CRO é obrigatório")
    @Size(min = 5, max = 15, message = "O CRO deve ter entre 5 e 15 caracteres")
    private String cro;

    @NotBlank(message = "A especialidade é obrigatória")
    private String especialidade;

    @Email(message = "Email inválido")
    @NotBlank(message = "O email é obrigatório")
    private String email;

    @NotBlank(message = "O telefone é obrigatório")
    private String telefone;
}