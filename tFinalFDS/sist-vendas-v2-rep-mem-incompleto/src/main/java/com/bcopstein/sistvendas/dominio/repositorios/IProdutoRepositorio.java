package com.bcopstein.sistvendas.dominio.repositorios;

import com.bcopstein.sistvendas.dominio.modelos.Produto;
import java.util.List;

public interface IProdutoRepositorio {
    List<Produto> todos();

    Produto porId(long id);
}
