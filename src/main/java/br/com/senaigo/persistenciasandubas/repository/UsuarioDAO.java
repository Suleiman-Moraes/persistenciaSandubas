package br.com.senaigo.persistenciasandubas.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.senaigo.persistenciasandubas.model.Usuario;

public interface UsuarioDAO extends JpaRepository<Usuario, Long> {
	Page<Usuario> findByNomeIgnoreCaseContainingAndLoginIgnoreCaseContainingAndStatusUsuarioEnumIgnoreCaseContainingAndFuncaoUsuarioEnumIgnoreCaseContainingOrderByIdDesc(
			String nome, String login, String statusUsuarioEnum, String funcaoUsuarioEnum, Pageable pages);

	Page<Usuario> findById(Long id, Pageable pages);
}
