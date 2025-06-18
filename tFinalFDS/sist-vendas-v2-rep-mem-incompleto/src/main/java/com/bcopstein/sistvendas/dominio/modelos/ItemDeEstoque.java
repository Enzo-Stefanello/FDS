package com.bcopstein.sistvendas.dominio.modelos;

import jakarta.persistence.*; //serve para usar as anotações do JPA

@Entity
public class ItemDeEstoque {
    @Id
    private long id;

    @ManyToOne(cascade = CascadeType.REFRESH) // serve para evitar o problema de entidade transiente
    private Produto produto;

    private int quantidade;
    private int estoqueMin;
    private int estoqueMax;

    protected ItemDeEstoque() {}

    public ItemDeEstoque(long id, Produto produto, int quantidade, int estoqueMin, int estoqueMax) {
        this.id = id;
        this.produto = produto;
        this.quantidade = quantidade;
        this.estoqueMin = estoqueMin;
        this.estoqueMax = estoqueMax;
    }

    public long getId() { return id; }
    public Produto getProduto() { return produto; }
    public int getQuantidade() { return quantidade; }
    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }
}
