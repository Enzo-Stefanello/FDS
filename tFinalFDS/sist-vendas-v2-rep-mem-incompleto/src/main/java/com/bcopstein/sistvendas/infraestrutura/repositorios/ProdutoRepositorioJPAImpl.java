package com.bcopstein.sistvendas.infraestrutura.repositorios;

import com.bcopstein.sistvendas.dominio.modelos.Produto;
import com.bcopstein.sistvendas.dominio.repositorios.IProdutoRepositorio;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Primary
public class ProdutoRepositorioJPAImpl implements IProdutoRepositorio {
    private final ProdutoJPA jpa;

    public ProdutoRepositorioJPAImpl(ProdutoJPA jpa) {
        this.jpa = jpa;
    }

    public List<Produto> todos() {
        return jpa.findAll();
    }

    public Produto porId(long id) {
        return jpa.findById(id).orElse(null);
    }
}
