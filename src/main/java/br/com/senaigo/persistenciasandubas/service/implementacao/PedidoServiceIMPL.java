package br.com.senaigo.persistenciasandubas.service.implementacao;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.senaigo.persistenciasandubas.model.Pedido;
import br.com.senaigo.persistenciasandubas.model.User;
import br.com.senaigo.persistenciasandubas.repository.PedidoDAO;
import br.com.senaigo.persistenciasandubas.repository.hql.GenercicDAO;
import br.com.senaigo.persistenciasandubas.service.DetalhePedidoService;
import br.com.senaigo.persistenciasandubas.service.PedidoService;
import br.com.senaigo.persistenciasandubas.service.UserService;
import lombok.Getter;

@Getter
@Service
public class PedidoServiceIMPL implements PedidoService{
	
	@Autowired
	private PedidoDAO persistencia;
	
	@Autowired
	private GenercicDAO genericDAO;

	@Autowired
	private DetalhePedidoService detalhePedidoService;
	
	@Autowired
	private UserService userService;
	
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
	
	@Override
	public Pedido adicionar(Pedido objeto) throws Exception {
		try {
			validarPedido(objeto);
			return genericDAO.update(objeto);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public Pedido getPedidoAtual(Long userId) throws Exception{
		try {
			if(userId == null) {
				throw new Exception("Usuário não informado");
			}
			Pedido pedido = persistencia.findByDataIsNullAndUserId(userId);
			if(pedido == null) {
				pedido = new Pedido();
				pedido.setValorTotal(0.0);
				pedido.setDetalhePedidos(new LinkedList<>());
				User user = userService.findById(userId + "");
				if(user == null) {
					throw new Exception("Usuário não encontrado");
				}
				pedido.setUser(user);
				pedido = save(pedido);
			}
			return pedido;
		} catch (Exception e) {
			throw e;
		}
	}
	
	@Override
	public Pedido getPedidoUserIdOpenLast(Long userId) throws Exception {
		try {
			if(userId == null) {
				throw new Exception("Usuário não informado");
			}
			List<Pedido> pedidos = persistencia.findByDataPedidoIsNullAndDataIsNotNullAndUserId(userId);
			if(pedidos != null && !pedidos.isEmpty()) {
				return pedidos.get(0);
			}
			throw new Exception("Pedido não encontrado.");
		} catch (Exception e) {
			throw e;
		}
	}
	
	private void validarPedido(Pedido objeto) throws Exception {
		if(objeto.getUser() == null) {
			throw new Exception("Usuário não informado");
		}
		if(objeto.getDetalhePedidos() == null || objeto.getDetalhePedidos().isEmpty()) {
			throw new Exception("Nenhum item do pedido foi informado");
		}
		detalhePedidoService.validar(objeto.getDetalhePedidos().get(objeto.getDetalhePedidos().size()-1));
		
	}
}
