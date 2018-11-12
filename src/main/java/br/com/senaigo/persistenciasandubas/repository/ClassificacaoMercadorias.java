package br.com.senaigo.persistenciasandubas.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.senaigo.persistenciasandubas.model.ClassificacaoMercadoria;

public interface ClassificacaoMercadorias extends JpaRepository<ClassificacaoMercadoria, Long>{
	public Optional<ClassificacaoMercadoria> findById(Long id);
	@SuppressWarnings("unchecked")
	public ClassificacaoMercadoria save(ClassificacaoMercadoria objeto);
}
