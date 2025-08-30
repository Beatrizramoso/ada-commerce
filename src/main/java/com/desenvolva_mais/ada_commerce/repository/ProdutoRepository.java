package com.desenvolva_mais.ada_commerce.repository;

import com.desenvolva_mais.ada_commerce.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
