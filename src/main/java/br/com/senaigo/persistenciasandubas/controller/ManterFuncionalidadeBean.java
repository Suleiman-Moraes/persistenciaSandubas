package br.com.senaigo.persistenciasandubas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

import br.com.senaigo.persistenciasandubas.model.Funcionalidade;
import br.com.senaigo.persistenciasandubas.response.Response;
import br.com.senaigo.persistenciasandubas.service.FuncionalidadeService;
import br.com.senaigo.persistenciasandubas.util.RestControllerUtil;

@RestController
@RequestMapping("/funcionalidade")
@CrossOrigin(origins = "*")
public class ManterFuncionalidadeBean {

	@Autowired
    private FuncionalidadeService service;

	@GetMapping
    public ResponseEntity<Response<List<Funcionalidade>>> findAll() {
        return ResponseEntity.ok(RestControllerUtil.findAll(service));
    }
 
    @GetMapping(value = "{id}")
    public ResponseEntity<Response<Funcionalidade>> findById(@PathVariable("id") String id) {
    	return ResponseEntity.ok(RestControllerUtil.findById(service, id));
    }
 
    @PostMapping
    public ResponseEntity<Response<Funcionalidade>> newObject(@RequestBody Funcionalidade objeto) {
    	return ResponseEntity.ok(RestControllerUtil.save(service, objeto));
    }
 
    @PutMapping
    public ResponseEntity<Response<Funcionalidade>> update(@RequestBody Funcionalidade objeto) {
    	return ResponseEntity.ok(RestControllerUtil.save(service, objeto));
    }
 
    @DeleteMapping(value = "{id}")
    public ResponseEntity<Response<Boolean>> deleteById(@PathVariable("id") String id) {
    	return ResponseEntity.ok(RestControllerUtil.deleteById(service, id));
    }
    
    @GetMapping(value = "{page}/{count}/{id}/{descricao}")
	public ResponseEntity<Response<Page<Funcionalidade>>> findByParams(@PathVariable("page") Integer page,
			@PathVariable("count") Integer count, @PathVariable("id") Long id, @PathVariable("descricao") String descricao) {
    	Response<Page<Funcionalidade>> response = new Response<>();
    	try {
    		Page<Funcionalidade> pagina = service.paginarComParemetros(page, count, id, descricao);
    		response.setData(pagina);
    		return ResponseEntity.ok(response);
		} catch (Exception e) {
			return ResponseEntity.ok(RestControllerUtil.mostrarErroPadraoObject(response, e.getMessage()));
		}
	}
    
    @GetMapping(value = "field={field}/value={value}")
    public ResponseEntity<Response<Funcionalidade>> findByField(@PathVariable("field") String field, 
    		@PathVariable("value") String value) {
    	return ResponseEntity.ok(RestControllerUtil.findByField(service, field, value));
    }
    
    @GetMapping(value = "/exists/field={field}/value={value}")
    public ResponseEntity<Response<Boolean>> existsByField(@PathVariable("field") String field, 
    		@PathVariable("value") String value) {
    	return ResponseEntity.ok(RestControllerUtil.existsByField(service, field, value));
    }
}
