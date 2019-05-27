package br.com.senaigo.persistenciasandubas.service;

import org.springframework.stereotype.Component;

import br.com.senaigo.persistenciasandubas.model.Pedido;
import br.com.senaigo.persistenciasandubas.util.CRUDPadraoService;

@Component
public interface PedidoService extends CRUDPadraoService<Pedido> {

	Pedido adicionar(Pedido objeto) throws Exception;

	Pedido getPedidoAtual(Long userId) throws Exception;

}