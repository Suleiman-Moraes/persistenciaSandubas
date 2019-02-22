package br.com.senaigo.persistenciasandubas.service.implementacao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.senaigo.persistenciasandubas.model.Telefone;
import br.com.senaigo.persistenciasandubas.repository.TelefoneDAO;
import br.com.senaigo.persistenciasandubas.repository.hql.GenercicDAO;
import br.com.senaigo.persistenciasandubas.service.TelefoneService;
import lombok.Getter;

@Getter
@Service
public class TelefoneServiceIMPL implements TelefoneService{
	
	@Autowired
	private TelefoneDAO persistencia;
	
	@Autowired
	private GenercicDAO genericDAO;

	@Override
	public List<Telefone> findAll() {
		return persistencia.findAll();
	}

	@Override
	public Telefone findById(String id) {
		return persistencia.findById(new Long(id)).get();
	}

	@Override
	public Telefone save(Telefone objeto) throws Exception {
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
			Boolean objeto = genericDAO.existsByField(Telefone.class, fieldName, value);
			return objeto;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public Telefone findByField(String field, String value) {
		try {
			Telefone objeto = genericDAO.findByField(Telefone.class, field, value);
			return objeto;
		} catch (Exception e) {
			throw e;
		}
	}
}
