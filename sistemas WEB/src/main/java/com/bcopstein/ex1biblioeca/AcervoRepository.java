package com.bcopstein.ex1biblioeca;

import java.util.List;

public interface AcervoRepository {

    List<Livro> getAll();

    List<String> getTitulos();

    List<String> getAutores();

    List<Integer> getAnoo();

    List<Livro> getLivrosDoAutor(String autor);

    Livro getLivroTitulo(String titulo);

    boolean cadastraLivroNovo(Livro livro);

    boolean removeLivro(int codigo);

}
