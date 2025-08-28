package com.desenvolva_mais.ada_commerce.controller;

import com.desenvolva_mais.ada_commerce.model.Cliente;
import com.desenvolva_mais.ada_commerce.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService service;

    // Criar um novo cliente
    @PostMapping
    public ResponseEntity<Cliente> criar(@RequestBody Cliente cliente) {
        Cliente novoCliente = service.cadastrarCliente(cliente);
        return ResponseEntity.status(201).body(novoCliente);
    }

    // Atualizar um cliente existente
    @PutMapping
    public ResponseEntity<Cliente> atualizar(@RequestBody Cliente cliente) {
        Cliente clienteAtualizado = service.atualizarCliente(cliente);
        return ResponseEntity.ok(clienteAtualizado);
    }

    // Listar todos os clientes
    @GetMapping
    public ResponseEntity<List<Cliente>> listarTodos() {
        return ResponseEntity.ok(service.listarClientes());
    }

    // Buscar um cliente pelo documento
    @GetMapping("/{documento}")
    public ResponseEntity<Cliente> buscarPorDocumento(@PathVariable String documento) {
        Cliente cliente = service.buscarCliente(documento);

        if (cliente == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(cliente);
    }

}
