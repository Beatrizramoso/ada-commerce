package com.desenvolva_mais.ada_commerce.unit;

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

/**
 * Serviço responsável por gerenciar as operações relacionadas a produtos.
 * <p>
 * Engloba criação, atualização, listagem e busca de produtos.
 */
@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository repository;

    /**
     * Cadastra um novo produto.
     * <p>
     * Se o produto já existir (mesmo nome), lança ResponseStatusException 400 (Bad Request).
     * Se for novo, persiste no banco.
     */
    public Produto cadastrarProduto(Produto produto) {
        if (repository.findByNome(produto.getNome()) != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Produto já cadastrado");
        }

        validarProduto(produto);

        produto.setId(null);

        return repository.save(produto);
    }

    /**
     * Atualiza os dados de um produto existente.
     * <p>
     * Se o produto não existir, lança ResponseStatusException 400 (Bad Request).
     * Se existir, atualiza os dados no banco.
     */
    public Produto atualizarProduto(Produto produto) {
        Optional<Produto> produtoSalvo = repository.findById(produto.getId());
        if (produtoSalvo.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Produto não existe");
        }

        validarProduto(produto);

        return repository.save(produto);
    }

    /**
     * Lista todos os produtos cadastrados.
     */
    public List<Produto> listarProdutos() {
        return repository.findAll();
    }

    /**
     * Busca um produto pelo ID.
     * <p>
     * Se o produto não existir, retorna null e o controller trata como HTTP 404.
     */
    public Produto buscarProduto(Long id) {
        return repository.findById(id).orElse(null);
    }

    /**
     * Valida os campos obrigatórios do produto.
     */
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
}
