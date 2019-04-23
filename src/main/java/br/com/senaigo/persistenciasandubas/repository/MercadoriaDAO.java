package br.com.senaigo.persistenciasandubas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.senaigo.persistenciasandubas.model.Mercadoria;

public interface MercadoriaDAO extends JpaRepository<Mercadoria, Long>{
	
}
