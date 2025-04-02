package com.bcopstein.ex1biblioeca;

import java.util.LinkedList;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    private List<Livro> livros;

    public Controller() {
        livros = new LinkedList<>();
        livros.add(new Livro(100, "Aprendendo Spring-Boot", "Huguinho Pato", 2020));
        livros.add(new Livro(120, "Aprendendo Java", "Zezinho Pato", 2015));
        livros.add(new Livro(140, "Aprendendo Outra coisa", "Luizinho Pato", 2023));
        livros.add(new Livro(140, "Aprendendo Uma coisa nova", "Huguinho Pato", 2023));
        livros.add(new Livro(140, "Aprendendo Outra coisa nova", "Huguinho Pato", 2023));
    }

    @GetMapping("")
    @CrossOrigin(origins = "*")
    public String mensagemDeBemVindo() {
        return "Bem vindo a biblioteca central!";
    }

    @GetMapping("livros")
    @CrossOrigin(origins = "*")
    public List<Livro> getListaLivros() {
        return livros;
    }

    @GetMapping("autores")
    @CrossOrigin(origins = "*")
    public List<String> getListaAutores() {
        return livros.stream()
                .map(l -> l.getAutor())
                .distinct()
                .toList();
    }

    @GetMapping("titulos")
    @CrossOrigin(origins = "*")
    public List<String> getListaTitulos() {
        return livros.stream()
                .map(l -> l.getTitulo())
                .toList();
    }

    // a) LivrosPorAno?ano=<valor> → devolve a lista dos livros publicados no ano indicado usando uma query string
    @GetMapping("/ano/{ano}")
    @CrossOrigin(origins = "*")
    public List<Livro> getAno(@PathVariable(value = "ano") int ano) {
        return livros.stream()
                .filter(livro -> livro.getAno() == ano)
                .toList();
    }

    // b) Desatualizados/{ano} → devolve a lista dos livros cujo ano de publicação é inferior ao ano informado
    @GetMapping("/desatualizados/{ano}")
    @CrossOrigin(origins = "*")
    public List<Livro> getDesatualizados(@PathVariable(value = "ano") int ano) {
        return livros.stream()
                .filter(livro -> livro.getAno() < ano)
                .toList();
    }

    //c) Crie uma rota que permita consultar todos os livros de um determinado autor publicados em um determinado ano
    @GetMapping("/autor/{autor}/ano/{ano}")
    @CrossOrigin(origins = "*")
    public List<Livro> getAutorAno(@PathVariable(value = "autor") String autor, 
                                    @PathVariable(value = "ano") int ano) {
        return livros.stream()
                .filter(livro -> livro.getAutor().equals(autor))
                .filter(livro -> livro.getAno() == ano)
                .toList();
    }
}