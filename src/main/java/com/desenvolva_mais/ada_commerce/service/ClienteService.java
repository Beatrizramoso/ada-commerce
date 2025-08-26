package com.desenvolva_mais.ada_commerce.service;

import com.desenvolva_mais.ada_commerce.model.Cliente;

import java.util.ArrayList;
import java.util.List;

public class ClienteService {

    public Cliente cadastrarCliente(Cliente cliente) {
        return cliente;
    }

    public Cliente atualizarCliente(Cliente cliente) {
        return cliente;
    }

    public List<Cliente> listarClientes() {
        return new ArrayList<>();
    }

    public Cliente buscarCliente(String documento) {
        return new Cliente();
    }

}
