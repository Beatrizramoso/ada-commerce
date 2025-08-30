package com.desenvolva_mais.ada_commerce.repository;

import com.desenvolva_mais.ada_commerce.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Cliente findByDocumento(String documento);

}
