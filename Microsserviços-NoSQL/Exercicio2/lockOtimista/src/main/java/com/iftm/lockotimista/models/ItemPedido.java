package com.iftm.lockotimista.models;

import jakarta.persistence.*;

@Entity
@Table(name = "item_pedido")
public class ItemPedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_item")
    private Integer codItem;

    @Column(name = "qtd_item")
    private Integer qtdItem;

    @ManyToOne
    @JoinColumn(name = "cod_produto", referencedColumnName = "cod_produto")
    private Produto produto;

    public ItemPedido(ItemPedido itemPedido) {
    }
}