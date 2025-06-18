package com.bcopstein.sistvendas.infraestrutura.repositorios;

import com.bcopstein.sistvendas.dominio.modelos.ItemDeEstoque;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstoqueJPA extends JpaRepository<ItemDeEstoque, Long> {
}
