package br.com.senaigo.persistenciasandubas.security.jwt;

public class JwtUserFactory {
	private JwtUserFactory() {}
	
//	public static JwtUser create(User objeto) {
//		return new JwtUser(objeto.getId(), objeto.getEmail(), objeto.getPassword(), mapToGrantedAthorities(objeto.getProfileEnum()));
//	}
	public static JwtUser create(Object objeto) {
		return null;
	}
	
//	private static List<GrantedAuthority> mapToGrantedAthorities(ProfileEnum objeto) {
//		List<GrantedAuthority> listaGrantedAuthority = new ArrayList<>();
//		listaGrantedAuthority.add(new SimpleGrantedAuthority(objeto.toString()));
//		return listaGrantedAuthority;
//	}
}
