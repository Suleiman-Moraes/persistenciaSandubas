package br.com.senaigo.persistenciasandubas.service.implementacao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.senaigo.persistenciasandubas.model.Pedido;
import br.com.senaigo.persistenciasandubas.repository.PedidoDAO;
import br.com.senaigo.persistenciasandubas.repository.hql.GenercicDAO;
import br.com.senaigo.persistenciasandubas.service.PedidoService;
import lombok.Getter;

@Getter
@Service
public class PedidoServiceIMPL implements PedidoService{
	
	@Autowired
	private PedidoDAO persistencia;
	
	@Autowired
	private GenercicDAO genericDAO;

	@Override
	public List<Pedido> findAll() {
		return persistencia.findAll();
	}

	@Override
	public Pedido findById(String id) {
		return persistencia.findById(new Long(id)).get();
	}

	@Override
	public Pedido save(Pedido objeto) throws Exception {
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
			Boolean objeto = genericDAO.existsByField(Pedido.class, fieldName, value);
			return objeto;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public Pedido findByField(String field, String value) {
		try {
			Pedido objeto = genericDAO.findByField(Pedido.class, field, value);
			return objeto;
		} catch (Exception e) {
			throw e;
		}
	}
}
