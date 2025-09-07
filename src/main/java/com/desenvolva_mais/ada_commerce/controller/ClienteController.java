package com.desenvolva_mais.ada_commerce.controller;

import com.desenvolva_mais.ada_commerce.model.Cliente;
import com.desenvolva_mais.ada_commerce.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller responsável por gerenciar as requisições HTTP relacionadas a clientes.
 * <p>
 * Permite criar, atualizar, listar e buscar clientes.
 */
@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService service;

    /**
     * Cria um novo cliente.
     * <p>
     * Retorna 400 Bad Request se o cliente já existir.
     * Retorna 201 Created com o cliente no corpo da resposta se criado com sucesso.
     */
    @PostMapping
    public ResponseEntity<Cliente> criar(@RequestBody Cliente cliente) {
        Cliente novoCliente = service.cadastrarCliente(cliente);
        return ResponseEntity.status(201).body(novoCliente);
    }

    /**
     * Atualiza um cliente existente.
     * <p>
     * Retorna 400 Bad Request se o cliente não existir.
     * Retorna 200 OK com o cliente atualizado se a atualização for bem-sucedida.
     */
    @PutMapping
    public ResponseEntity<Cliente> atualizar(@RequestBody Cliente cliente) {
        Cliente clienteAtualizado = service.atualizarCliente(cliente);
        return ResponseEntity.ok(clienteAtualizado);
    }

    /**
     * Lista todos os clientes cadastrados.
     * <p>
     * Retorna 200 OK com todos os clientes encontrados.
     */
    @GetMapping
    public ResponseEntity<List<Cliente>> listarTodos() {
        return ResponseEntity.ok(service.listarClientes());
    }

    /**
     * Busca um cliente pelo documento.
     * <p>
     * Retorna 200 OK se o cliente existir.
     * Retorna 404 Not Found se não encontrado.
     */
    @GetMapping("/{documento}")
    public ResponseEntity<Cliente> buscarPorDocumento(@PathVariable String documento) {
        Cliente cliente = service.buscarCliente(documento);
        return cliente != null ? ResponseEntity.ok(cliente) : ResponseEntity.notFound().build();
    }

}
