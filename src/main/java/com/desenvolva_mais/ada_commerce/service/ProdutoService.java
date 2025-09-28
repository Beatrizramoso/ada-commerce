package com.desenvolva_mais.ada_commerce.service;

import com.desenvolva_mais.ada_commerce.model.Produto;
import com.desenvolva_mais.ada_commerce.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository repository;


    public Produto cadastrarProduto(Produto produto) {
        if (repository.findByNome(produto.getNome()) != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Produto já cadastrado");
        }

        validarProduto(produto);
        produto.setId(null);

        Produto salvo = repository.save(produto);

        // Envia log de criação em uma thread separada
        new Thread(() -> logCriacaoProduto(salvo)).start();

        return salvo;
    }


    public Produto atualizarProduto(Produto produto) {
        Optional<Produto> produtoSalvo = repository.findById(produto.getId());
        if (produtoSalvo.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Produto não existe");
        }

        validarProduto(produto);

        Produto atualizado = repository.save(produto);

        // Envia log de atualização em uma thread separada
        new Thread(() -> logAtualizacaoProduto(atualizado)).start();

        return atualizado;
    }


    public List<Produto> listarProdutos() {
        return repository.findAll();
    }


    public Produto buscarProduto(Long id) {
        return repository.findById(id).orElse(null);
    }


    private void validarProduto(Produto produto) {
        if (Strings.isBlank(produto.getNome())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nome é obrigatório");
        }

        if (Strings.isBlank(produto.getDescricao())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Descrição é obrigatória");
        }

        if (produto.getValorBase() == null || produto.getValorBase().compareTo(BigDecimal.ZERO) <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Valor base deve ser maior que zero");
        }
    }


    private void logCriacaoProduto(Produto produto) {
        try {
            Thread.sleep(2000); // Simula tempo de envio
            System.out.println("📦 Produto cadastrado: " + produto.getNome() + " | Valor: " + produto.getValorBase());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Erro ao registrar log de criação do produto: " + e.getMessage());
        }
    }

        private void logAtualizacaoProduto(Produto produto) {
        try {
            Thread.sleep(2000); // Simula tempo de envio
            System.out.println("🛠️ Produto atualizado: " + produto.getNome() + " | Novo valor: " + produto.getValorBase());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Erro ao registrar log de atualização do produto: " + e.getMessage());
        }
    }
}
