package br.com.senaigo.persistenciasandubas.security.model;

import br.com.senaigo.persistenciasandubas.model.Usuario;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CurrentUser {
	
	private String token;
	private Usuario user;
	
	public CurrentUser(String token, Usuario user) {
		super();
		this.token = token;
		this.user = user;
	}
}
