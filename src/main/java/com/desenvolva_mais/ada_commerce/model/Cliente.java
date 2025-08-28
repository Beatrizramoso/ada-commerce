package com.desenvolva_mais.ada_commerce.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@ToString
@Getter
@Setter
@AllArgsConstructor
public class Cliente {

    private final UUID id;

    private String nome;

    private String documento;

    private String email;

}
