package com.desenvolva_mais.ada_commerce.unit;

import com.desenvolva_mais.ada_commerce.model.Cliente;
import com.desenvolva_mais.ada_commerce.repository.ClienteRepository;
import com.desenvolva_mais.ada_commerce.service.ClienteService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClienteServiceTest {

    @Mock
    private ClienteRepository repository;

    @InjectMocks
    private ClienteService service;

    @Test
    void deveCadastrarCliente() {
        Cliente cliente = new Cliente(null, "Maria", "11122233344", "maria@email.com");

        when(repository.findByDocumento(cliente.getDocumento())).thenReturn(null);
        when(repository.save(any(Cliente.class))).thenReturn(cliente);

        Cliente salvo = service.cadastrarCliente(cliente);

        assertEquals("Maria", salvo.getNome());
        verify(repository, times(1)).save(cliente);
    }

    @Test
    void naoDeveCadastrarClienteDuplicado() {
        Cliente cliente = new Cliente(1L, "Maria", "11122233344", "maria@email.com");

        when(repository.findByDocumento(cliente.getDocumento())).thenReturn(cliente);

        assertThrows(ResponseStatusException.class, () -> service.cadastrarCliente(cliente));
    }
}
