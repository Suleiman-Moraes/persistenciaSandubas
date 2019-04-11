package br.com.senaigo.persistenciasandubas.service;

import br.com.senaigo.persistenciasandubas.model.User;
import br.com.senaigo.persistenciasandubas.util.CRUDPadraoService;

public interface UserService extends CRUDPadraoService<User>{

	User login(String login, String senha) throws Exception;

}
