package com.desenvolva_mais.ada_commerce;

import java.util.*;

public class Cliente {
    private final UUID id;
    private String nome;
    private String documento;
    private String email;

    public Cliente(String nome, String documento, String email) {
        this.id = UUID.randomUUID();
        this.nome = nome;
        this.documento = documento;
        this.email = email;
    }

    public UUID getId() { return id;}
    public String getNome() { return nome;}
    public String getDocumento() { return documento;}
    public String getEmail() { return email;}
    public void setNome(String nome) { this.nome = nome; }
    public void setEmail(String email) { this.email = email; }
}
