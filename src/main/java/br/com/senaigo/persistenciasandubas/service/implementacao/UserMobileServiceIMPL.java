package br.com.senaigo.persistenciasandubas.service.implementacao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.senaigo.persistenciasandubas.model.UserMobile;
import br.com.senaigo.persistenciasandubas.repository.UserMobileDAO;
import br.com.senaigo.persistenciasandubas.repository.hql.GenercicDAO;
import br.com.senaigo.persistenciasandubas.service.UserMobileService;

public class UserMobileServiceIMPL implements UserMobileService{
	
	@Autowired
	private UserMobileDAO persistencia;
	
	@Autowired
	private GenercicDAO genericDAO;

	@Override
	public List<UserMobile> findAll() {
		return persistencia.findAll();
	}

	@Override
	public UserMobile findById(String id) {
		return persistencia.findById(new Long(id)).get();
	}

	@Override
	public UserMobile save(UserMobile objeto) throws Exception {
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
			Boolean objeto = genericDAO.existsByField(UserMobile.class, fieldName, value);
			return objeto;
		} catch (Exception e) {
			throw e;
		}
	}
	
	@Override
	public UserMobile findByField(String field, String value) {
		try {
			UserMobile objeto = genericDAO.findByField(UserMobile.class, field, value);
			return objeto;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public UserMobile login(String login, String senha) throws Exception {
		try {
			UserMobile objeto = persistencia.findByLoginAndSenha(login, senha);
			if(objeto == null) {
				throw new Exception("Usuário ou senha incorretos");
			}
			return objeto;
		} catch (Exception e) {
			throw e;
		}
	}
}
