package br.com.senaigo.persistenciasandubas.service;

import org.springframework.stereotype.Component;

import br.com.senaigo.persistenciasandubas.model.DetalhePedido;
import br.com.senaigo.persistenciasandubas.util.CRUDPadraoService;

@Component
public interface DetalhePedidoService extends CRUDPadraoService<DetalhePedido> {

	void validar(DetalhePedido detalhePedido) throws Exception;

}
