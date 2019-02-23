package br.com.senaigo.persistenciasandubas.security.jwt;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import br.com.senaigo.persistenciasandubas.model.Usuario;
import br.com.senaigo.persistenciasandubas.model.enummeration.FuncaoUsuarioEnum;

public class JwtUserFactory {
	private JwtUserFactory() {}
	
	public static JwtUser create(Usuario objeto) {
		return new JwtUser(objeto.getId() + "", objeto.getLogin(), objeto.getSenha(), mapToGrantedAthorities(objeto.getFuncaoUsuarioEnum()));
	}
	public static JwtUser create(Object objeto) {
		return null;
	}
	
	private static List<GrantedAuthority> mapToGrantedAthorities(FuncaoUsuarioEnum objeto) {
		List<GrantedAuthority> listaGrantedAuthority = new ArrayList<>();
		listaGrantedAuthority.add(new SimpleGrantedAuthority(objeto.toString()));
		return listaGrantedAuthority;
	}
}
