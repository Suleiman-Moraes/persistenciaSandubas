package br.com.senaigo.persistenciasandubas.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.senaigo.persistenciasandubas.model.Usuario;
import br.com.senaigo.persistenciasandubas.model.enummeration.FuncaoUsuarioEnum;
import br.com.senaigo.persistenciasandubas.model.enummeration.StatusUsuarioEnum;

public interface UsuarioDAO extends JpaRepository<Usuario, Long> {
	Page<Usuario> findByNomeIgnoreCaseContainingAndLoginIgnoreCaseContainingOrderByIdDesc(
			String nome, String login, Pageable pages);
	
	Page<Usuario> findById(Long id, Pageable pages);
	
	Page<Usuario> findByStatusUsuarioEnum(StatusUsuarioEnum statusUsuarioEnum, Pageable pages);
	
	Page<Usuario> findByFuncaoUsuarioEnum(FuncaoUsuarioEnum funcaoUsuarioEnum, Pageable pages);
}
