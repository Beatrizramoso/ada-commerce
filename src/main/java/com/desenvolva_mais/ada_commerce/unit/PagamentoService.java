package com.desenvolva_mais.ada_commerce.unit;

import com.desenvolva_mais.ada_commerce.model.Pedido;
import com.desenvolva_mais.ada_commerce.model.enums.StatusPedido;
import com.desenvolva_mais.ada_commerce.repository.PedidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 * Serviço responsável por gerenciar os pagamentos de pedidos.
 * <p>
 * Regras:
 * - Só permite pagamento de pedidos no status AGUARDANDO_PAGAMENTO.
 * - Após sucesso, muda para PAGO e notifica o cliente.
 */
@Service
@RequiredArgsConstructor
public class PagamentoService {

    private final PedidoRepository pedidoRepository;
    private final NotificacaoService notificacaoService;

    /**
     * Realiza o pagamento de um pedido.
     *
     * @param idPedido ID do pedido a ser pago
     * @return Pedido atualizado com status PAGO
     */
    public Pedido realizarPagamento(Long idPedido) {
        Pedido pedido = pedidoRepository.findById(idPedido)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado"));

        if (pedido.getStatus() != StatusPedido.AGUARDANDO_PAGAMENTO) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Pagamento não permitido para este pedido");
        }

        pedido.setStatus(StatusPedido.PAGO);
        pedidoRepository.save(pedido);

        // Notifica cliente
        notificacaoService.enviarEmail(
                pedido.getCliente(),
                String.format("Pagamento confirmado para o pedido #%d. Obrigado pela sua compra!", pedido.getId())
        );

        return pedido;
    }
}
