package com.desenvolva_mais.ada_commerce.model;

import lombok.*;

import java.util.UUID;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {

    private UUID id;

    private String nome;

    private String documento;

    private String email;

}
