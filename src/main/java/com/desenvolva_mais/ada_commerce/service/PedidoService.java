package com.desenvolva_mais.ada_commerce.service;

import com.desenvolva_mais.ada_commerce.model.Pedido;
import com.desenvolva_mais.ada_commerce.repository.PedidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * Serviço responsável por gerenciar as operações relacionadas a Pedido.
 * <p>
 * Engloba criação, atualização, listagem, busca, finalização e entrega de pedidos.
 */
@Service
@RequiredArgsConstructor
public class PedidoService {

    private final PedidoRepository repository;

    public Pedido cadastrarPedido(String documentoCliente) {
        return new Pedido();
    }

    public List<Pedido> listarPedidos() {
        return repository.findAll();
    }

    public Pedido buscarPedido(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Pedido adicionarItem(Long idPedido, Long idProduto, int quantidade, BigDecimal valorVenda) {
        Pedido pedido = buscarPedido(idPedido);
        return pedido;
    }

    public Pedido alterarItem(Long idPedido, Long idProduto, int novaQuantidade, BigDecimal novoValorVenda) {
        Pedido pedido = buscarPedido(idPedido);
        return pedido;
    }

    public Pedido removerItem(Long idPedido, Long idProduto) {
        Pedido pedido = buscarPedido(idPedido);
        return pedido;
    }

    public Pedido finalizarPedido(Long idPedido) {
        Pedido pedido = buscarPedido(idPedido);
        // pedido.setStatus()
        return pedido;
    }

    public Pedido entregarPedido(Long idPedido) {
        Pedido pedido = buscarPedido(idPedido);
        // pedido.setStatus()
        return pedido;
    }

}
