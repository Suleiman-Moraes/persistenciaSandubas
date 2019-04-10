package br.com.senaigo.persistenciasandubas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.senaigo.persistenciasandubas.model.UserMobile;

public interface UserMobileDAO extends JpaRepository<UserMobile, Long>{
	UserMobile findByLoginAndSenha(String login, String senha);
}
