package br.com.senaigo.persistenciasandubas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.senaigo.persistenciasandubas.model.Pedido;
import br.com.senaigo.persistenciasandubas.response.Response;
import br.com.senaigo.persistenciasandubas.service.PedidoService;
import br.com.senaigo.persistenciasandubas.util.RestControllerUtil;

@RestController
@RequestMapping("/pedido")
@CrossOrigin(origins = "*")
public class ManterPedidoBean {

	@Autowired
    private PedidoService service;

	@GetMapping
    public ResponseEntity<Response<List<Pedido>>> findAll() {
        return ResponseEntity.ok(RestControllerUtil.findAll(service));
    }
 
    @GetMapping(value = "{id}")
    public ResponseEntity<Response<Pedido>> findById(@PathVariable("id") String id) {
    	return ResponseEntity.ok(RestControllerUtil.findById(service, id));
    }
 
    @PostMapping
    public ResponseEntity<Response<Pedido>> newObject(@RequestBody Pedido objeto) {
    	return ResponseEntity.ok(RestControllerUtil.save(service, objeto));
    }
 
    @PutMapping
    public ResponseEntity<Response<Pedido>> update(@RequestBody Pedido objeto) {
    	return ResponseEntity.ok(RestControllerUtil.save(service, objeto));
    }
 
    @DeleteMapping(value = "{id}")
    public ResponseEntity<Response<Boolean>> deleteById(@PathVariable("id") String id) {
    	return ResponseEntity.ok(RestControllerUtil.deleteById(service, id));
    }
    
    @GetMapping(value = "field={field}/value={value}")
    public ResponseEntity<Response<Pedido>> findByField(@PathVariable("field") String field, 
    		@PathVariable("value") String value) {
    	return ResponseEntity.ok(RestControllerUtil.findByField(service, field, value));
    }
    
    @GetMapping(value = "/exists/field={field}/value={value}")
    public ResponseEntity<Response<Boolean>> existsByField(@PathVariable("field") String field, 
    		@PathVariable("value") String value) {
    	return ResponseEntity.ok(RestControllerUtil.existsByField(service, field, value));
    }
}
