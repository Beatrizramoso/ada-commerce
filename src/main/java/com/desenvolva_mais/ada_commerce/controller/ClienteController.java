package com.desenvolva_mais.ada_commerce.controller;


import com.desenvolva_mais.ada_commerce.model.Cliente;
import com.desenvolva_mais.ada_commerce.service.ClienteService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService service;

    // Criar um novo cliente
    @PostMapping
    public ResponseEntity<Cliente> criar(@RequestBody Cliente cliente) {
        try {
            return ResponseEntity.ok(service.cadastrarCliente(cliente));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // Atualiza um cliente
    @PutMapping()
    public ResponseEntity<Cliente> atualizar(@RequestBody Cliente cliente) {
        try {
            return ResponseEntity.ok(service.atualizarCliente(cliente));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // Listar todos clientes
    @GetMapping
    public ResponseEntity<List<Cliente>> listarTodos() {
        List<Cliente> clientes = service.listarClientes();
        return ResponseEntity.ok(clientes);
    }

    // Buscar um cliente pelo documento
    @GetMapping("/{documento}")
    public ResponseEntity<Cliente> buscarPorId(@PathVariable String documento) {
        Cliente cliente = service.buscarCliente(documento);

        if (cliente == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(cliente);
    }

}

