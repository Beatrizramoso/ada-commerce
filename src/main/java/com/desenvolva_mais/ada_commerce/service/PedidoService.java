package com.desenvolva_mais.ada_commerce.service;

import com.desenvolva_mais.ada_commerce.model.Pedido;

import java.math.BigDecimal;

public class PedidoService {

    public Pedido cadastrarPedido(String documentoCliente) {
        return new Pedido();
    }

    public Pedido buscarPedido(Long id) {
        return new Pedido();
    }

    public Pedido adicionarItem(Long idPedido, Long idProduto, int quantidade, BigDecimal valorVenda) {
        return new Pedido();
    }

    public Pedido alterarItem(Long idPedido, Long idProduto, int novaQuantidade, BigDecimal novoValorVenda) {
        return new Pedido();
    }

    public Pedido removerItem(Long idPedido, Long idProduto) {
        return new Pedido();
    }

    public Pedido finalizarPedido(Long idPedido) {
        return new Pedido();
    }

    public Pedido entregarPedido(Long idPedido) {
        return new Pedido();
    }

}
