package com.desenvolva_mais.ada_commerce.service;

import com.desenvolva_mais.ada_commerce.model.Pedido;
import com.desenvolva_mais.ada_commerce.model.enums.StatusPedido;
import com.desenvolva_mais.ada_commerce.repository.PedidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
@RequiredArgsConstructor
public class PagamentoService {

    private final PedidoRepository pedidoRepository;
    private final NotificacaoService notificacaoService;


    public Pedido realizarPagamento(Long idPedido) {
        Pedido pedido = pedidoRepository.findById(idPedido)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado"));

        if (pedido.getStatus() != StatusPedido.AGUARDANDO_PAGAMENTO) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Pagamento não permitido para este pedido");
        }

        pedido.setStatus(StatusPedido.PAGO);
        pedidoRepository.save(pedido);

        // Envia notificação em uma thread separada
        new Thread(() -> notificacaoService.enviarEmail(
                pedido.getCliente(),
                String.format("Pagamento confirmado para o pedido #%d. Obrigado pela sua compra!", pedido.getId())
        )).start();

        return pedido;
    }
}
