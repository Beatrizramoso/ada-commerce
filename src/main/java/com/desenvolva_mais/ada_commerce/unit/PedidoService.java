package com.desenvolva_mais.ada_commerce.unit;

import com.desenvolva_mais.ada_commerce.model.*;
import com.desenvolva_mais.ada_commerce.model.enums.StatusPedido;
import com.desenvolva_mais.ada_commerce.repository.ClienteRepository;
import com.desenvolva_mais.ada_commerce.repository.PedidoRepository;
import com.desenvolva_mais.ada_commerce.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * Serviço responsável por gerenciar as operações relacionadas a Pedido.
 * <p>
 * Engloba criação, atualização, listagem, busca, finalização e entrega de pedidos.
 */
@Service
@RequiredArgsConstructor
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ClienteRepository clienteRepository;
    private final ProdutoRepository produtoRepository;
    private final NotificacaoService notificacaoService;

    /**
     * Cria um novo pedido para o cliente informado.
     * Status inicial: ABERTO
     */
    public Pedido criarPedido(String documentoCliente) {
        Cliente cliente = Optional.ofNullable(clienteRepository.findByDocumento(documentoCliente))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cliente não encontrado"));

        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setStatus(StatusPedido.ABERTO);

        return pedidoRepository.save(pedido);
    }

    public Pedido buscarPedido(Long id) {
        return pedidoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado"));
    }

    /**
     * Adiciona um item a um pedido.
     */
    public Pedido adicionarItem(Long idPedido, Long idProduto, int quantidade, BigDecimal valorVenda) {
        Pedido pedido = buscarPedido(idPedido);

        if (pedido.getStatus() != StatusPedido.ABERTO) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Somente pedidos ABERTOS podem ser alterados");
        }

        Produto produto = produtoRepository.findById(idProduto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Produto não encontrado"));

        ItemPedido item = new ItemPedido();
        item.setPedido(pedido);
        item.setProduto(produto);
        item.setQuantidade(quantidade);
        item.setValorVenda(valorVenda);

        pedido.getItens().add(item);

        return pedidoRepository.save(pedido);
    }

    /**
     * Altera quantidade e/ou valor de um item já existente.
     */
    public Pedido alterarItem(Long idPedido, Long idItem, int novaQuantidade, BigDecimal novoValorVenda) {
        Pedido pedido = buscarPedido(idPedido);

        ItemPedido item = pedido.getItens().stream()
                .filter(i -> i.getId().equals(idItem))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Item não encontrado no pedido"));

        item.setQuantidade(novaQuantidade);
        item.setValorVenda(novoValorVenda);

        return pedidoRepository.save(pedido);
    }

    /**
     * Remove um item do pedido.
     */
    public Pedido removerItem(Long idPedido, Long idItem) {
        Pedido pedido = buscarPedido(idPedido);

        boolean removed = pedido.getItens().removeIf(i -> i.getId().equals(idItem));
        if (!removed) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Item não encontrado no pedido");
        }

        return pedidoRepository.save(pedido);
    }

    /**
     * Finaliza um pedido, validando se tem itens e valor > 0.
     * Status muda para AGUARDANDO_PAGAMENTO.
     */
    public Pedido finalizarPedido(Long idPedido) {
        Pedido pedido = buscarPedido(idPedido);

        if (pedido.getItens().isEmpty() ||
                pedido.getItens().stream().map(ItemPedido::getValorVenda).reduce(BigDecimal.ZERO, BigDecimal::add)
                        .compareTo(BigDecimal.ZERO) <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Pedido inválido: deve conter itens com valor > 0");
        }

        pedido.setStatus(StatusPedido.AGUARDANDO_PAGAMENTO);

        Pedido atualizado = pedidoRepository.save(pedido);
        notificacaoService.enviarEmail(pedido.getCliente(), "Seu pedido foi finalizado e aguarda pagamento.");
        return atualizado;
    }

    /**
     * Entrega um pedido (somente se PAGO).
     * Status muda para FINALIZADO.
     */
    public Pedido entregarPedido(Long idPedido) {
        Pedido pedido = buscarPedido(idPedido);

        if (pedido.getStatus() != StatusPedido.PAGO) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Somente pedidos pagos podem ser entregues");
        }

        pedido.setStatus(StatusPedido.FINALIZADO);

        Pedido atualizado = pedidoRepository.save(pedido);
        notificacaoService.enviarEmail(pedido.getCliente(), "Seu pedido foi entregue!");
        return atualizado;
    }

    public List<Pedido> listarPedidos() {
        return pedidoRepository.findAll();
    }
}
