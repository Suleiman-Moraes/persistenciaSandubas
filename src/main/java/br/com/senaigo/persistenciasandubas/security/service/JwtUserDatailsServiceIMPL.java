//package br.com.senaigo.persistenciasandubas.security.service;
//
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//@Service
//public class JwtUserDatailsServiceIMPL implements UserDetailsService{
//
//	@Autowired
//	private UserService service;
//	
//	@Override
//	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//		User user = service.findByEmail(email);
//		if(user == null) {
//			throw new UsernameNotFoundException(String.format("Usuário não encontrado com o e-mail \"%s\".", email));
//		}
//		else {
//			return JwtUserFactory.create(user);
//		}
//		return null;//TODO
//	}
//	
//}
