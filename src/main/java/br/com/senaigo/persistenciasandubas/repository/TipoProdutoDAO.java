package br.com.senaigo.persistenciasandubas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.senaigo.persistenciasandubas.model.TipoProduto;

public interface TipoProdutoDAO extends JpaRepository<TipoProduto, Long>{

}
