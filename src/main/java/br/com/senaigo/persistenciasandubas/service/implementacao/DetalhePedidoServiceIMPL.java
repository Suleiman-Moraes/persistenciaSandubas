package br.com.senaigo.persistenciasandubas.service.implementacao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.senaigo.persistenciasandubas.model.DetalhePedido;
import br.com.senaigo.persistenciasandubas.model.Pedido;
import br.com.senaigo.persistenciasandubas.repository.DetalhePedidoDAO;
import br.com.senaigo.persistenciasandubas.repository.hql.GenercicDAO;
import br.com.senaigo.persistenciasandubas.service.DetalhePedidoService;
import br.com.senaigo.persistenciasandubas.service.PedidoService;
import lombok.Getter;

@Getter
@Service
public class DetalhePedidoServiceIMPL implements DetalhePedidoService{
	
	@Autowired
	private DetalhePedidoDAO persistencia;
	
	@Autowired
	private GenercicDAO genericDAO;
	
	@Autowired
	private PedidoService pedidoService;

	@Override
	public List<DetalhePedido> findAll() {
		return persistencia.findAll();
	}

	@Override
	public DetalhePedido findById(String id) {
		return persistencia.findById(new Long(id)).get();
	}

	@Override
	public DetalhePedido save(DetalhePedido objeto) throws Exception {
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
			Boolean objeto = genericDAO.existsByField(DetalhePedido.class, fieldName, value);
			return objeto;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public DetalhePedido findByField(String field, String value) {
		try {
			DetalhePedido objeto = genericDAO.findByField(DetalhePedido.class, field, value);
			return objeto;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public void validar(DetalhePedido detalhePedido) throws Exception {
		if(detalhePedido.getTotal() == null || detalhePedido.getTotal() <=0 ) {
			throw new Exception("Total não informado");
		}
		if(detalhePedido.getQuantidade() == null || detalhePedido.getQuantidade() <=0 ) {
			throw new Exception("Quantidade não informado");
		}
		if(detalhePedido.getPrecoUnitario() == null || detalhePedido.getPrecoUnitario() <=0 ) {
			throw new Exception("Preço não informado");
		}
		if(detalhePedido.getMercadoria() == null) {
			throw new Exception("Mercadoria não informado");
		}
	}

	@Override
	public DetalhePedido adicionar(DetalhePedido objeto, Long userId) throws Exception {
		try {
			Pedido pedido = pedidoService.getPedidoAtual(userId);
			pedido.getDetalhePedidos().add(objeto);
			pedido = pedidoService.adicionar(pedido);
			return pedido.getDetalhePedidos().get(pedido.getDetalhePedidos().size() - 1);
		} catch (Exception e) {
			return null;
		}
	}
}
