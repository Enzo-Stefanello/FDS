package com.bcopstein.ex1biblioeca;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    private AcervoRepository livros;

    @Autowired
    public Controller(AcervoRepository livros) {
        this.livros = livros;
    }

    @GetMapping("")
    @CrossOrigin(origins = "*")
    public String mensagemDeBemVindo() {
        return "Bem vindo a biblioteca central!";
    }

    @GetMapping("livros")
    @CrossOrigin(origins = "*")
    public List<Livro> getListaLivros() {
        return livros.getAll();
    }

    @GetMapping("autores")
    @CrossOrigin(origins = "*")
    public List<String> getListaAutores() {
        return livros.getAutores();

    }

    @GetMapping("titulos")
    @CrossOrigin(origins = "*")
    public List<String> getListaTitulos() {
        return livros.getTitulos();

    }

    // a) LivrosPorAno?ano=<valor> → devolve a lista dos livros publicados no ano
    // indicado usando uma query string
    @GetMapping("/ano/{ano}")
    @CrossOrigin(origins = "*")
    public List<Livro> getAno(@PathVariable(value = "ano") int ano) {
        return livros.getAll().stream()
                .filter(livro -> livro.getAno() == ano)
                .toList();
    }

    // b) Desatualizados/{ano} → devolve a lista dos livros cujo ano de publicação é
    // inferior ao ano informado
    @GetMapping("/desatualizados/{ano}")
    @CrossOrigin(origins = "*")
    public List<Livro> getDesatualizados(@PathVariable(value = "ano") int ano) {
        return livros.getAll().stream()
                .filter(livro -> livro.getAno() < ano)  
                .toList();
    }

    // c) Crie uma rota que permita consultar todos os livros de um determinado
    // autor publicados em um determinado ano
    @GetMapping("/autor/{autor}/ano/{ano}")
    @CrossOrigin(origins = "*")
    public List<Livro> getAutorAno(@PathVariable(value = "autor") String autor,
            @PathVariable(value = "ano") int ano) {
            return livros.getLivrosDoAutor(autor)
                .stream()
                .filter(livro -> livro.getAno() == ano)
                .toList();
    }

     @PostMapping("/novolivro")
    @CrossOrigin(origins = "*")
    public boolean cadastraLivroNovo(@RequestBody final Livro livro) {
        return livros.cadastraLivroNovo(livro);
    }
}