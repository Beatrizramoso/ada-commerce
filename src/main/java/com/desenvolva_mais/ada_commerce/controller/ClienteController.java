package com.desenvolva_mais.ada_commerce.controller;

import com.desenvolva_mais.ada_commerce.dto.ClienteCompletoDTO;
import com.desenvolva_mais.ada_commerce.dto.CriarClienteDTO;
import com.desenvolva_mais.ada_commerce.dto.converter.ClienteConverter;
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
    public ResponseEntity<ClienteCompletoDTO> criar(@RequestBody CriarClienteDTO cliente) {
        Cliente novoCliente = service.cadastrarCliente(ClienteConverter.toModel(cliente));
        return ResponseEntity.status(201).body(ClienteConverter.toDTO(novoCliente));
    }

    /**
     * Atualiza um cliente existente.
     * <p>
     * Retorna 400 Bad Request se o cliente não existir.
     * Retorna 200 OK com o cliente atualizado se a atualização for bem-sucedida.
     */
    @PutMapping
    public ResponseEntity<ClienteCompletoDTO> atualizar(@RequestBody ClienteCompletoDTO cliente) {
        Cliente clienteAtualizado = service.atualizarCliente(ClienteConverter.toModel(cliente));
        return ResponseEntity.ok(ClienteConverter.toDTO(clienteAtualizado));
    }

    /**
     * Lista todos os clientes cadastrados.
     * <p>
     * Retorna 200 OK com todos os clientes encontrados.
     */
    @GetMapping
    public ResponseEntity<List<ClienteCompletoDTO>> listarTodos() {
        return ResponseEntity.ok(
                service.listarClientes()
                        .stream()
                        .map(ClienteConverter::toDTO)
                        .toList()
        );
    }

    /**
     * Busca um cliente pelo documento.
     * <p>
     * Retorna 200 OK se o cliente existir.
     * Retorna 404 Not Found se não encontrado.
     */
    @GetMapping("/{documento}")
    public ResponseEntity<ClienteCompletoDTO> buscarPorDocumento(@PathVariable String documento) {
        Cliente cliente = service.buscarCliente(documento);
        return cliente != null ? ResponseEntity.ok(ClienteConverter.toDTO(cliente)) : ResponseEntity.notFound().build();
    }

}
