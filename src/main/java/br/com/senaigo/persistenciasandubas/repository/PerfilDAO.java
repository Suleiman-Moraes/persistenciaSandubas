package br.com.senaigo.persistenciasandubas.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.senaigo.persistenciasandubas.model.Perfil;

public interface PerfilDAO extends JpaRepository<Perfil, Long>{
	Page<Perfil> findByNomeIgnoreCaseContainingAndDescricaoIgnoreCaseContainingAndFuncionalidadesIdOrderByIdDesc(
			String nome, String descricao, Long funcionalidadesId, Pageable pages);
	
	Page<Perfil> findByNomeIgnoreCaseContainingAndDescricaoIgnoreCaseContainingOrderByIdDesc(
			String nome, String descricao, Pageable pages);
	
	Page<Perfil> findById(Long id, Pageable pages);
}
