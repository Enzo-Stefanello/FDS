package com.bcopstein.sistvendas.interfaces.controladores;

import com.bcopstein.sistvendas.aplicacao.casosuso.ConsultaCatalogo;
import com.bcopstein.sistvendas.dominio.modelos.Produto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class CatalogoController {
    private final ConsultaCatalogo consultaCatalogo;

    public CatalogoController(ConsultaCatalogo consultaCatalogo) {
        this.consultaCatalogo = consultaCatalogo;
    }

    @GetMapping
    public List<Produto> listar() {
        return consultaCatalogo.executar();
    }
}
