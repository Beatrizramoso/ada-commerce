package com.desenvolva_mais.ada_commerce.service;

import com.desenvolva_mais.ada_commerce.model.Produto;

import java.util.ArrayList;
import java.util.List;

public class ProdutoService {

    public Produto cadastrarProduto(Produto produto) {
        return produto;
    }

    public Produto atualizarProduto(Produto produto) {
        return produto;
    }

    public List<Produto> listarProdutos() {
        return new ArrayList<>();
    }

    public Produto buscarProduto(Long id) {
        return new Produto();
    }

}
