package com.desenvolva_mais.ada_commerce.controller;

import com.desenvolva_mais.ada_commerce.model.Produto;
import com.desenvolva_mais.ada_commerce.service.ProdutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller responsável por gerenciar as requisições HTTP relacionadas a produtos.
 * <p>
 * Permite criar, atualizar, listar e buscar produtos.
 */
@RestController
@RequestMapping("/produtos")
@RequiredArgsConstructor
public class ProdutoController {

    private final ProdutoService service;

    /**
     * Cria um novo produto.
     * <p>
     * Retorna 400 Bad Request se o produto já existir.
     * Retorna 201 Created com o produto no corpo da resposta se criado com sucesso.
     */
    @PostMapping
    public ResponseEntity<Produto> criar(@RequestBody Produto produto) {
        Produto novoProduto = service.cadastrarProduto(produto);
        return ResponseEntity.status(201).body(novoProduto);
    }

    /**
     * Atualiza um produto existente.
     * <p>
     * Retorna 400 Bad Request se o produto não existir.
     * Retorna 200 OK com o produto atualizado se a atualização for bem-sucedida.
     */
    @PutMapping
    public ResponseEntity<Produto> atualizar(@RequestBody Produto produto) {
        Produto produtoAtualizado = service.atualizarProduto(produto);
        return ResponseEntity.ok(produtoAtualizado);
    }

    /**
     * Lista todos os produtos cadastrados.
     * <p>
     * Retorna 200 OK com todos os produtos encontrados.
     */
    @GetMapping
    public ResponseEntity<List<Produto>> listarTodos() {
        return ResponseEntity.ok(service.listarProdutos());
    }

    /**
     * Busca um produto pelo ID.
     * <p>
     * Retorna 200 OK se o produto existir.
     * Retorna 404 Not Found se não encontrado.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscarPorId(@PathVariable Long id) {
        Produto produto = service.buscarProduto(id);
        return produto != null ? ResponseEntity.ok(produto) : ResponseEntity.notFound().build();
    }

}
