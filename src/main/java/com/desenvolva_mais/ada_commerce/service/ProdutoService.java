package com.desenvolva_mais.ada_commerce.service;

import com.desenvolva_mais.ada_commerce.model.Produto;
import com.desenvolva_mais.ada_commerce.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Serviço responsável por gerenciar as operações relacionadas a Produto.
 * <p>
 * Engloba criação, atualização, listagem e busca de produtos.
 */
@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository repository;

    public Produto cadastrarProduto(Produto produto) {
        return repository.save(produto);
    }

    public Produto atualizarProduto(Produto produto) {
        return repository.save(produto);
    }

    public List<Produto> listarProdutos() {
        return repository.findAll();
    }

    public Produto buscarProduto(Long id) {
        return repository.findById(id).orElse(null);
    }

}
