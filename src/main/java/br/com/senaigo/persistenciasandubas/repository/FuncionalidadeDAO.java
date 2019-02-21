package br.com.senaigo.persistenciasandubas.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.senaigo.persistenciasandubas.model.Funcionalidade;

public interface FuncionalidadeDAO extends JpaRepository<Funcionalidade, Long>{
	Page<Funcionalidade> findByDescricaoIgnoreCaseContainingOrderByIdDesc(
			String descricao, Pageable pages);
	
	Page<Funcionalidade> findById(Long id, Pageable pages);
}
