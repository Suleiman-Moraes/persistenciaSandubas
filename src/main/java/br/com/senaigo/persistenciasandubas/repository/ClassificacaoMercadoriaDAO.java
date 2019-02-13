package br.com.senaigo.persistenciasandubas.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.senaigo.persistenciasandubas.model.ClassificacaoMercadoria;

public interface ClassificacaoMercadoriaDAO extends JpaRepository<ClassificacaoMercadoria, Long> {
	Page<ClassificacaoMercadoria> findByIdContainingAndNomeIgnoreCaseContainingAndDescricaoIgnoreCaseContainingOrderByIdDesc(
			Long id, String nome, String descricao, Pageable pages);
}
