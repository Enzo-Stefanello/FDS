package com.bcopstein.sistvendas.infraestrutura.repositorios;

import com.bcopstein.sistvendas.dominio.modelos.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoJPA extends JpaRepository<Produto, Long> {
}
