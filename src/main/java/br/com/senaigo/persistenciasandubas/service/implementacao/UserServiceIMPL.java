package br.com.senaigo.persistenciasandubas.service.implementacao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.senaigo.persistenciasandubas.model.User;
import br.com.senaigo.persistenciasandubas.repository.UserDAO;
import br.com.senaigo.persistenciasandubas.repository.hql.GenercicDAO;
import br.com.senaigo.persistenciasandubas.service.UserService;
import lombok.Getter;

@Getter
@Service
public class UserServiceIMPL implements UserService{
	
	@Autowired
	private UserDAO persistencia;
	
	@Autowired
	private GenercicDAO genericDAO;

	@Override
	public List<User> findAll() {
		return persistencia.findAll();
	}

	@Override
	public User findById(String id) {
		return persistencia.findById(new Long(id)).get();
	}

	@Override
	public User save(User objeto) throws Exception {
		try {
			return persistencia.save(objeto);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public Boolean deleteById(String id) {
		try {
			persistencia.deleteById(new Long(id));
			return Boolean.TRUE;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public Boolean existsByField(String fieldName, String value) throws Exception {
		try {
			Boolean objeto = genericDAO.existsByField(User.class, fieldName, value);
			return objeto;
		} catch (Exception e) {
			throw e;
		}
	}
	
	@Override
	public User findByField(String field, String value) {
		try {
			User objeto = genericDAO.findByField(User.class, field, value);
			return objeto;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public User login(String login, String senha) throws Exception {
		try {
			User objeto = persistencia.findByLoginAndSenha(login, senha);
			if(objeto == null) {
				throw new Exception("Usuário ou senha incorretos");
			}
			return objeto;
		} catch (Exception e) {
			throw e;
		}
	}
}
