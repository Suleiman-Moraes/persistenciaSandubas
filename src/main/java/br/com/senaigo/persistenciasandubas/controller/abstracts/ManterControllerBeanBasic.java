package br.com.senaigo.persistenciasandubas.controller.abstracts;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.senaigo.persistenciasandubas.model.interfaces.IControlador;
import br.com.senaigo.persistenciasandubas.response.Response;
import br.com.senaigo.persistenciasandubas.util.RestControllerUtil;

public abstract class ManterControllerBeanBasic<T> implements IControlador<T>{
	
	@GetMapping
	@Override
    public ResponseEntity<Response<List<T>>> findAll() {
        return RestControllerUtil.findAllCompleto(getService());
    }
 
    @GetMapping(value = "{id}")
	@Override
    public ResponseEntity<Response<T>> findById(@PathVariable("id") String id) {
    	return RestControllerUtil.findByIdCompleto(getService(), id);
    }
 
    @PostMapping
	@Override
    public ResponseEntity<Response<T>> newObject(@RequestBody T objeto) {
    	return RestControllerUtil.saveCompleto(getService(), objeto);
    }
 
    @PutMapping
    public ResponseEntity<Response<T>> update(@RequestBody T objeto) {
    	return RestControllerUtil.saveCompleto(getService(), objeto);
    }
 
    @DeleteMapping(value = "{id}")
	@Override
    public ResponseEntity<Response<Boolean>> deleteById(@PathVariable("id") String id) {
    	return RestControllerUtil.deleteByIdCompleto(getService(), id);
    }
    
    @GetMapping(value = "/findbyfield")
	@Override
    public ResponseEntity<Response<T>> findByField(@RequestParam("field") String field, 
    		@RequestParam("value") String value) {
    	return RestControllerUtil.findByFieldCompleto(getService(), field, value);
    }
    
    @GetMapping(value = "/exists")
	@Override
    public ResponseEntity<Response<Boolean>> existsByField(@RequestParam("field") String field, 
    		@RequestParam("value") String value) {
    	return RestControllerUtil.existsByFieldCompleto(getService(), field, value);
    }
}
