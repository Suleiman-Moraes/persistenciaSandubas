package br.com.senaigo.persistenciasandubas.service;

import org.springframework.stereotype.Component;

import br.com.senaigo.persistenciasandubas.model.UserMobile;
import br.com.senaigo.persistenciasandubas.util.CRUDPadraoService;

@Component
public interface UserMobileService extends CRUDPadraoService<UserMobile>{

	UserMobile login(String login, String senha) throws Exception;

}
