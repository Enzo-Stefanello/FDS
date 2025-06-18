package com.bcopstein.sistvendas.aplicacao.casosuso;

import com.bcopstein.sistvendas.dominio.modelos.Produto;
import com.bcopstein.sistvendas.dominio.repositorios.IProdutoRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultaCatalogo {
    private final IProdutoRepositorio repositorio;

    public ConsultaCatalogo(IProdutoRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    public List<Produto> executar() {
        return repositorio.todos();
    }
}
