package com.desenvolva_mais.ada_commerce.service;

import com.desenvolva_mais.ada_commerce.model.Cliente;
import org.springframework.stereotype.Service;

@Service
public class NotificacaoService {


    public void enviarEmail(Cliente cliente, String mensagem) {
        new Thread(() -> {
            try {
                System.out.println("📤 Iniciando envio de e-mail para " + cliente.getEmail());
                Thread.sleep(3000); // Simulando tempo de envio
                System.out.println("✅ E-mail enviado para " + cliente.getNome() + " (" + cliente.getEmail() + "): " + mensagem);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("❌ Falha ao enviar e-mail para " + cliente.getEmail());
            }
        }).start();
    }
}
