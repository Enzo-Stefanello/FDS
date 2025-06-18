package com.bcopstein.sistvendas.dominio.repositorios;

import com.bcopstein.sistvendas.dominio.modelos.ItemDeEstoque;
import java.util.Optional;

public interface IEstoqueRepositorio {
    Optional<ItemDeEstoque> porId(long id);
    ItemDeEstoque salvar(ItemDeEstoque item);
}
