package br.com.senaigo.persistenciasandubas.service.implementacao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.senaigo.persistenciasandubas.model.Mercadoria;
import br.com.senaigo.persistenciasandubas.repository.MercadoriaDAO;
import br.com.senaigo.persistenciasandubas.repository.hql.GenercicDAO;
import br.com.senaigo.persistenciasandubas.service.MercadoriaService;
import lombok.Getter;

@Getter
@Service
public class MercadoriaServiceIMPL implements MercadoriaService{
	
	@Autowired
	private MercadoriaDAO persistencia;
	
	@Autowired
	private GenercicDAO genericDAO;

	@Override
	public List<Mercadoria> findAll() {
		return persistencia.findAll();
	}

	@Override
	public Mercadoria findById(String id) {
		return persistencia.findById(new Long(id)).get();
	}

	@Override
	public Mercadoria save(Mercadoria objeto) throws Exception {
		try {
			return genericDAO.update(objeto);
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
			Boolean objeto = genericDAO.existsByField(Mercadoria.class, fieldName, value);
			return objeto;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public Mercadoria findByField(String field, String value) {
		try {
			Mercadoria objeto = genericDAO.findByField(Mercadoria.class, field, value);
			return objeto;
		} catch (Exception e) {
			throw e;
		}
	}
}
