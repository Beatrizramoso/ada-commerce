package com.desenvolva_mais.ada_commerce.service;

import com.desenvolva_mais.ada_commerce.model.Cliente;
import com.desenvolva_mais.ada_commerce.repository.ClienteRepository;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ClienteService {

    private final ClienteRepository repository;

    @SneakyThrows
    public Cliente cadastrarCliente(Cliente cliente) {
        if (repository.findByDocumento(cliente.getDocumento()) != null) {
            throw new BadRequestException("Cliente já cadastrado");
        }
        cliente.setId(UUID.randomUUID());
        return repository.save(cliente);
    }

    @SneakyThrows
    public Cliente atualizarCliente(Cliente cliente) {
        if (repository.findByDocumento(cliente.getDocumento()) == null) {
            throw new BadRequestException("Cliente não existe cadastrado");
        }
        return repository.save(cliente);
    }

    public List<Cliente> listarClientes() {
        return repository.findAll();
    }

    public Cliente buscarCliente(String documento) {
        return repository.findByDocumento(documento);
    }

}
