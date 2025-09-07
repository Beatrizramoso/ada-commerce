package com.desenvolva_mais.ada_commerce.model;

import com.desenvolva_mais.ada_commerce.model.enums.StatusPedido;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Pedido {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    private LocalDateTime dataCriacao;

    @Enumerated(EnumType.STRING)
    private StatusPedido status = StatusPedido.ABERTO;

    private String observacaoEntrega;

    // Removemos do ToString para não gerar StackOverflowError
    @ToString.Exclude
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemPedido> itens = new ArrayList<>();

}
