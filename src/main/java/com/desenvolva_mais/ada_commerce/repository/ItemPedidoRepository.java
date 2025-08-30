package com.desenvolva_mais.ada_commerce.repository;

import com.desenvolva_mais.ada_commerce.model.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Long> {

}
