package com.desenvolva_mais.ada_commerce.unit;

import com.desenvolva_mais.ada_commerce.model.Cliente;
import com.desenvolva_mais.ada_commerce.model.Pedido;
import com.desenvolva_mais.ada_commerce.model.enums.StatusPedido;
import com.desenvolva_mais.ada_commerce.repository.PedidoRepository;
import com.desenvolva_mais.ada_commerce.service.NotificacaoService;
import com.desenvolva_mais.ada_commerce.service.PagamentoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PagamentoServiceTest {

    @Mock
    private PedidoRepository pedidoRepository;

    @Mock
    private NotificacaoService notificacaoService;

    @InjectMocks
    private PagamentoService pagamentoService;

    @Test
    void deveRealizarPagamento() {
        Pedido pedido = new Pedido();
        pedido.setId(1L);
        pedido.setStatus(StatusPedido.AGUARDANDO_PAGAMENTO);
        pedido.setCliente(new Cliente(1L, "João", "12345678901", "joao@email.com"));

        when(pedidoRepository.findById(1L)).thenReturn(Optional.of(pedido));

        Pedido pago = pagamentoService.realizarPagamento(1L);

        assertEquals(StatusPedido.PAGO, pago.getStatus());
        verify(pedidoRepository, times(1)).save(pedido);
        verify(notificacaoService, times(1)).enviarEmail(any(), contains("Pagamento confirmado"));
    }

    @Test
    void naoDevePermitirPagamentoSeStatusInvalido() {
        Pedido pedido = new Pedido();
        pedido.setId(1L);
        pedido.setStatus(StatusPedido.ABERTO);

        when(pedidoRepository.findById(1L)).thenReturn(Optional.of(pedido));

        assertThrows(ResponseStatusException.class, () -> pagamentoService.realizarPagamento(1L));
    }
}
