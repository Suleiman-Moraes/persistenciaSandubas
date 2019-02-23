package br.com.senaigo.persistenciasandubas.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.senaigo.persistenciasandubas.model.Usuario;
import br.com.senaigo.persistenciasandubas.security.jwt.JwtUserFactory;
import br.com.senaigo.persistenciasandubas.service.UsuarioService;

@Service
public class JwtUserDatailsServiceIMPL implements UserDetailsService{

	@Autowired
	private UsuarioService service;
	
	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		Usuario user = service.findByField("login", login);
		if(user == null) {
			throw new UsernameNotFoundException(String.format("Usuário não encontrado com o e-mail \"%s\".", login));
		}
		else {
			return JwtUserFactory.create(user);
		}
	}
}
