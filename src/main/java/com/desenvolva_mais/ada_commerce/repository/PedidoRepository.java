package com.desenvolva_mais.ada_commerce.repository;

import com.desenvolva_mais.ada_commerce.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

}
