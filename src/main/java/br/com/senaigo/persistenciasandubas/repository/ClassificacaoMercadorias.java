package br.com.senaigo.persistenciasandubas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.senaigo.persistenciasandubas.model.ClassificacaoMercadoria;

public interface ClassificacaoMercadorias extends JpaRepository<ClassificacaoMercadoria, Long>{
}
