package com.bcopstein.sistvendas.infraestrutura.repositorios;

import com.bcopstein.sistvendas.dominio.modelos.ItemDeEstoque;
import com.bcopstein.sistvendas.dominio.repositorios.IEstoqueRepositorio;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Primary
public class EstoqueRepositorioJPAImpl implements IEstoqueRepositorio {
    private final EstoqueJPA estoqueJPA;

    public EstoqueRepositorioJPAImpl(EstoqueJPA estoqueJPA) {
        this.estoqueJPA = estoqueJPA;
    }

    public Optional<ItemDeEstoque> porId(long id) {
        return estoqueJPA.findById(id);
    }

    public ItemDeEstoque salvar(ItemDeEstoque item) {
        return estoqueJPA.save(item);
    }
}
