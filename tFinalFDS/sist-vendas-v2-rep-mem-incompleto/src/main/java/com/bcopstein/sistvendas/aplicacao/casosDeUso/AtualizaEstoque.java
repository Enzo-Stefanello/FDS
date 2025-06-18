package com.bcopstein.sistvendas.aplicacao.casosuso;

import com.bcopstein.sistvendas.aplicacao.dto.EntradaEstoqueDTO;
import com.bcopstein.sistvendas.dominio.modelos.ItemDeEstoque;
import com.bcopstein.sistvendas.dominio.repositorios.IEstoqueRepositorio;
import org.springframework.stereotype.Service;

@Service
public class AtualizaEstoque {
    private final IEstoqueRepositorio repositorio;

    public AtualizaEstoque(IEstoqueRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    public boolean executar(EntradaEstoqueDTO entrada) { 
        var optItem = repositorio.porId(entrada.getIdItem());
        if (optItem.isEmpty())
            return false;

        ItemDeEstoque item = optItem.get();
        item.setQuantidade(item.getQuantidade() + entrada.getQuantidade());
        repositorio.salvar(item);
        return true;
    }
}
