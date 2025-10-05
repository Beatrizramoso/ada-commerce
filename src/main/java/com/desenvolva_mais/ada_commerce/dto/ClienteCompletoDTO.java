package com.desenvolva_mais.ada_commerce.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ClienteCompletoDTO {

    @EqualsAndHashCode.Include
    private Long id;

    @NotBlank(message = "O nome é obrigatório.")
    @Size(max = 100, message = "O nome deve ter no máximo 100 caracteres.")
    private String nome;

    @NotBlank(message = "O documento é obrigatório.")
    @Size(min = 11, max = 14, message = "O documento deve ter entre 11 e 14 caracteres.")
    private String documento;

    @Email(message = "O e-mail informado é inválido.")
    @NotBlank(message = "O e-mail é obrigatório.")
    @Size(max = 100, message = "O e-mail deve ter no máximo 100 caracteres.")
    private String email;
}
