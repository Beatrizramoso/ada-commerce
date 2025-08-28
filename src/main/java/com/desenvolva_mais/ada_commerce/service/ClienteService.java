package com.desenvolva_mais.ada_commerce.service;

import com.desenvolva_mais.ada_commerce.model.Cliente;
import com.desenvolva_mais.ada_commerce.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.UUID;

/**
 * Serviço responsável por gerenciar as operações relacionadas a clientes.
 * <p>
 * Engloba criação, atualização, listagem e busca de clientes.
 */
@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository repository;

    /**
     * Cadastra um novo cliente.
     * <p>
     * Se o cliente já existir (mesmo documento), lança ResponseStatusException 400 (Bad Request).
     * Se o cliente for novo, gera um ID único e persiste no banco.
     */
    public Cliente cadastrarCliente(Cliente cliente) {
        if (repository.findByDocumento(cliente.getDocumento()) != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cliente já cadastrado");
        }
        cliente.setId(UUID.randomUUID());
        return repository.save(cliente);
    }

    /**
     * Atualiza os dados de um cliente existente.
     * <p>
     * Se o cliente não existir, lança ResponseStatusException 400 (Bad Request).
     * Se o cliente existir, atualiza os dados no banco.
     */
    public Cliente atualizarCliente(Cliente cliente) {
        if (repository.findByDocumento(cliente.getDocumento()) == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cliente não existe cadastrado");
        }
        return repository.save(cliente);
    }

    /**
     * Lista todos os clientes cadastrados.
     */
    public List<Cliente> listarClientes() {
        return repository.findAll();
    }

    /**
     * Busca um cliente pelo documento.
     * <p>
     * Se o cliente não existir, retorna null e o controller trata como HTTP 404.
     * Se o cliente existir, retorna ele.
     */
    public Cliente buscarCliente(String documento) {
        return repository.findByDocumento(documento);
    }

}
