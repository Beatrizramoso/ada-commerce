package com.desenvolva_mais.ada_commerce.controller;

import com.desenvolva_mais.ada_commerce.model.Pedido;
import com.desenvolva_mais.ada_commerce.service.PedidoService;
import com.desenvolva_mais.ada_commerce.service.PagamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * Controller responsável por gerenciar as requisições HTTP relacionadas a pedidos.
 * <p>
 * Permite criar pedidos, adicionar/alterar/remover itens, finalizar, pagar e entregar pedidos.
 */
@RestController
@RequestMapping("/pedidos")
@RequiredArgsConstructor
public class PedidoController {

    private final PedidoService pedidoService;
    private final PagamentoService pagamentoService;

    /**
     * Cria um novo pedido para o cliente informado (pelo documento).
     */
    @PostMapping("/{documentoCliente}")
    public ResponseEntity<Pedido> criarPedido(@PathVariable String documentoCliente) {
        Pedido pedido = pedidoService.criarPedido(documentoCliente);
        return ResponseEntity.status(201).body(pedido);
    }

    /**
     * Lista todos os pedidos cadastrados.
     */
    @GetMapping
    public ResponseEntity<List<Pedido>> listarTodos() {
        return ResponseEntity.ok(pedidoService.listarPedidos());
    }

    /**
     * Busca um pedido pelo ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Pedido> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(pedidoService.buscarPedido(id));
    }

    /**
     * Adiciona um item ao pedido.
     */
    @PostMapping("/{idPedido}/itens")
    public ResponseEntity<Pedido> adicionarItem(
            @PathVariable Long idPedido,
            @RequestParam Long idProduto,
            @RequestParam int quantidade,
            @RequestParam BigDecimal valorVenda
    ) {
        Pedido pedido = pedidoService.adicionarItem(idPedido, idProduto, quantidade, valorVenda);
        return ResponseEntity.ok(pedido);
    }

    /**
     * Altera quantidade/valor de um item existente.
     */
    @PutMapping("/{idPedido}/itens/{idItem}")
    public ResponseEntity<Pedido> alterarItem(
            @PathVariable Long idPedido,
            @PathVariable Long idItem,
            @RequestParam int quantidade,
            @RequestParam BigDecimal valorVenda
    ) {
        Pedido pedido = pedidoService.alterarItem(idPedido, idItem, quantidade, valorVenda);
        return ResponseEntity.ok(pedido);
    }

    /**
     * Remove um item do pedido.
     */
    @DeleteMapping("/{idPedido}/itens/{idItem}")
    public ResponseEntity<Pedido> removerItem(
            @PathVariable Long idPedido,
            @PathVariable Long idItem
    ) {
        Pedido pedido = pedidoService.removerItem(idPedido, idItem);
        return ResponseEntity.ok(pedido);
    }

    /**
     * Finaliza um pedido.
     */
    @PutMapping("/{idPedido}/finalizar")
    public ResponseEntity<Pedido> finalizarPedido(@PathVariable Long idPedido) {
        Pedido pedido = pedidoService.finalizarPedido(idPedido);
        return ResponseEntity.ok(pedido);
    }

    /**
     * Realiza o pagamento de um pedido.
     */
    @PutMapping("/{idPedido}/pagar")
    public ResponseEntity<Pedido> pagarPedido(@PathVariable Long idPedido) {
        Pedido pedido = pagamentoService.realizarPagamento(idPedido);
        return ResponseEntity.ok(pedido);
    }

    /**
     * Entrega um pedido (somente se já pago).
     */
    @PutMapping("/{idPedido}/entregar")
    public ResponseEntity<Pedido> entregarPedido(@PathVariable Long idPedido) {
        Pedido pedido = pedidoService.entregarPedido(idPedido);
        return ResponseEntity.ok(pedido);
    }
}
