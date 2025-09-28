package com.desenvolva_mais.ada_commerce.service;

import com.desenvolva_mais.ada_commerce.model.Cliente;
import com.desenvolva_mais.ada_commerce.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository repository;


    public Cliente cadastrarCliente(Cliente cliente) {
        if (repository.findByDocumento(cliente.getDocumento()) != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cliente já cadastrado");
        }

        validarCliente(cliente);
        cliente.setId(null);
        Cliente clienteSalvo = repository.save(cliente);

        // Inicia uma nova thread para simular o envio de e-mail de boas-vindas
        new Thread(() -> enviarEmailBoasVindas(clienteSalvo)).start();

        return clienteSalvo;
    }


    public Cliente atualizarCliente(Cliente cliente) {
        Optional<Cliente> clienteSalvo = repository.findById(cliente.getId());
        if (clienteSalvo.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cliente não existe");
        }

        validarCliente(cliente);

        return repository.save(cliente);
    }

        public List<Cliente> listarClientes() {
        return repository.findAll();
    }

    /
    public Cliente buscarCliente(String documento) {
        return repository.findByDocumento(documento);
    }

        private void validarCliente(Cliente cliente) {
        if (Strings.isBlank(cliente.getDocumento())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Documento é obrigatório");
        }

        if (Strings.isBlank(cliente.getNome())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nome é obrigatório");
        }

        if (Strings.isBlank(cliente.getEmail())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email é obrigatório");
        }
    }


    private void enviarEmailBoasVindas(Cliente cliente) {
        try {
            System.out.println("📤 Iniciando envio de e-mail para: " + cliente.getEmail());

            // Simulando tempo de envio (ex: envio via servidor de e-mail)
            Thread.sleep(3000); // 3 segundos

            System.out.println("✅ E-mail de boas-vindas enviado para: " + cliente.getEmail());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("❌ Falha ao enviar e-mail para: " + cliente.getEmail());
        }
    }
}
