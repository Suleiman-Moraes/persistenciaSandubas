package br.com.senaigo.persistenciasandubas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.senaigo.persistenciasandubas.model.User;

public interface UserDAO extends JpaRepository<User, Long>{
	User findByLoginAndSenha(String login, String senha);
}
