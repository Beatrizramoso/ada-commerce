package com.desenvolva_mais.ada_commerce.dto;

import lombok.*;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CriarClienteDTO {

    @NotBlank(message = "Nome é obrigatório")
    @Size(max = 100)
    private String nome;

    @NotBlank(message = "Documento é obrigatório")
    private String documento;

    @Email(message = "E-mail inválido")
    @NotBlank(message = "E-mail é obrigatório")
    private String email;
}
