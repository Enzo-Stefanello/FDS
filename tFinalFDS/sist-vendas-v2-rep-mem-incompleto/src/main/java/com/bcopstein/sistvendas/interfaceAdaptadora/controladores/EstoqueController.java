package com.bcopstein.sistvendas.interfaces.controladores;

import com.bcopstein.sistvendas.aplicacao.casosuso.AtualizaEstoque;
import com.bcopstein.sistvendas.aplicacao.dto.EntradaEstoqueDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/estoque")
public class EstoqueController {
    private final AtualizaEstoque atualizaEstoque;

    public EstoqueController(AtualizaEstoque atualizaEstoque) {
        this.atualizaEstoque = atualizaEstoque;
    }

    @PostMapping
    public ResponseEntity<String> entradaEstoque(@RequestBody EntradaEstoqueDTO entrada) {
        boolean sucesso = atualizaEstoque.executar(entrada);
        if (sucesso) {
            return ResponseEntity.ok("Estoque atualizado com sucesso.");
        } else {
            return ResponseEntity.badRequest().body("Item de estoque não encontrado.");
        }
    }
}
