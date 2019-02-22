package br.com.senaigo.persistenciasandubas.service.implementacao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.senaigo.persistenciasandubas.model.Email;
import br.com.senaigo.persistenciasandubas.repository.EmailDAO;
import br.com.senaigo.persistenciasandubas.repository.hql.GenercicDAO;
import br.com.senaigo.persistenciasandubas.service.EmailService;
import lombok.Getter;

@Getter
@Service
public class EmailServiceIMPL implements EmailService{
	
	@Autowired
	private EmailDAO persistencia;
	
	@Autowired
	private GenercicDAO genericDAO;

	@Override
	public List<Email> findAll() {
		return persistencia.findAll();
	}

	@Override
	public Email findById(String id) {
		return persistencia.findById(new Long(id)).get();
	}

	@Override
	public Email save(Email objeto) throws Exception {
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
			Boolean objeto = genericDAO.existsByField(Email.class, fieldName, value);
			return objeto;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public Email findByField(String field, String value) {
		try {
			Email objeto = genericDAO.findByField(Email.class, field, value);
			return objeto;
		} catch (Exception e) {
			throw e;
		}
	}
}
