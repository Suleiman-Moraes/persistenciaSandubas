package br.com.senaigo.persistenciasandubas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.senaigo.persistenciasandubas.controller.abstracts.ManterControllerBeanBasic;
import br.com.senaigo.persistenciasandubas.model.Pedido;
import br.com.senaigo.persistenciasandubas.response.Response;
import br.com.senaigo.persistenciasandubas.service.PedidoService;
import br.com.senaigo.persistenciasandubas.util.RestControllerUtil;
import lombok.Getter;

@RestController
@RequestMapping("/pedido")
public class ManterPedidoBean extends ManterControllerBeanBasic<Pedido>{

	@Autowired
	@Getter
    private PedidoService service;

    @PutMapping(value="/adicionar")
    public ResponseEntity<Response<Pedido>> adicionar(@RequestBody Pedido objeto){
    	Response<Pedido> response = new Response<>();
    	try {
    		Pedido pedido = service.adicionar(objeto);
    		response.setData(pedido);
    		RestControllerUtil.logarInfo(this.getClass(), "adicionar == Sucesso");
    		return ResponseEntity.ok(response);
    	} catch (Exception e) {
    		return RestControllerUtil.mostrarErroPadraoObject(this.getClass(), response, e.getMessage());
    	}
    }
    
    @Deprecated
    @GetMapping(value="/getpedido/{userId}")
    public ResponseEntity<Response<Pedido>> getPedidoAtual(@PathVariable("userId") Long userId){
    	Response<Pedido> response = new Response<>();
    	try {
    		Pedido pedido = service.getPedidoAtual(userId);
    		response.setData(pedido);
    		return ResponseEntity.ok(response);
    	} catch (Exception e) {
    		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(RestControllerUtil.mostrarErroPadraoObject(response, e.getMessage()));
    	}
    }
    
    @GetMapping(value="/user/{userId}/last")
    public ResponseEntity<Response<Pedido>> getPedidoUserIdLast(@PathVariable("userId") Long userId){
    	Response<Pedido> response = new Response<>();
    	try {
    		Pedido pedido = service.getPedidoAtual(userId);
    		response.setData(pedido);
    		RestControllerUtil.logarInfo(this.getClass(), "getPedidoUserIdLast == Sucesso");
    		return ResponseEntity.ok(response);
    	} catch (Exception e) {
    		return RestControllerUtil.mostrarErroPadraoObject(this.getClass(), response, e.getMessage());
    	}
    }
    
    @GetMapping(value="/user/{userId}/openlast")
    public ResponseEntity<Response<Pedido>> getPedidoUserIdOpenLast(@PathVariable("userId") Long userId){
       	Response<Pedido> response = new Response<>();
    	try {
    		Pedido pedido = service.getPedidoUserIdOpenLast(userId);
    		response.setData(pedido);
    		RestControllerUtil.logarInfo(this.getClass(), "getPedidoUserIdLast == Sucesso");
    		return ResponseEntity.ok(response);
		} catch (Exception e) {
			return RestControllerUtil.mostrarErroPadraoObject(this.getClass(), response, e.getMessage());
		}
    }
}

