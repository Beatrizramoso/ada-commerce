package com.desenvolva_mais.ada_commerce.service;

import com.desenvolva_mais.ada_commerce.model.Cliente;
import org.springframework.stereotype.Service;

/**
 * Serviço responsável por enviar notificações para clientes.
 * <p>
 * Nesta versão, simula o envio de e-mails apenas logando no console.
 */
@Service
public class NotificacaoService {

    public void enviarEmail(Cliente cliente, String mensagem) {
        // Simulação de envio de e-mail
        System.out.println(
                "📧 Enviando e-mail para " + cliente.getNome() + " (" + cliente.getEmail() + ") -> " + mensagem);
    }
}
