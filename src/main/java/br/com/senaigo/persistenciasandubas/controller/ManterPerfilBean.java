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

import br.com.senaigo.persistenciasandubas.model.Perfil;
import br.com.senaigo.persistenciasandubas.response.Response;
import br.com.senaigo.persistenciasandubas.service.PerfilService;
import br.com.senaigo.persistenciasandubas.util.RestControllerUtil;

@RestController
@RequestMapping("/perfil")
@CrossOrigin(origins = "*")
public class ManterPerfilBean {

	@Autowired
    private PerfilService service;

	@GetMapping
    public ResponseEntity<Response<List<Perfil>>> findAll() {
        return ResponseEntity.ok(RestControllerUtil.findAll(service));
    }
 
    @GetMapping(value = "{id}")
    public ResponseEntity<Response<Perfil>> findById(@PathVariable("id") String id) {
    	return ResponseEntity.ok(RestControllerUtil.findById(service, id));
    }
 
    @PostMapping
    public ResponseEntity<Response<Perfil>> newObject(@RequestBody Perfil objeto) {
    	return ResponseEntity.ok(RestControllerUtil.save(service, objeto));
    }
 
    @PutMapping
    public ResponseEntity<Response<Perfil>> update(@RequestBody Perfil objeto) {
    	return ResponseEntity.ok(RestControllerUtil.save(service, objeto));
    }
 
    @DeleteMapping(value = "{id}")
    public ResponseEntity<Response<Boolean>> deleteById(@PathVariable("id") String id) {
    	return ResponseEntity.ok(RestControllerUtil.deleteById(service, id));
    }
    
    @GetMapping(value = "{page}/{count}/{id}/{nome}/{descricao}/{funcionalidadeId}")
	public ResponseEntity<Response<Page<Perfil>>> findByParams(@PathVariable("page") Integer page,
			@PathVariable("count") Integer count, @PathVariable("id") Long id, @PathVariable("nome") String nome, 
			@PathVariable("descricao") String descricao, @PathVariable("funcionalidadeId") Long funcionalidadeId) {
    	Response<Page<Perfil>> response = new Response<>();
    	try {
    		Page<Perfil> pagina = service.paginarComParemetros(page, count, id, nome, descricao, funcionalidadeId);
    		response.setData(pagina);
    		return ResponseEntity.ok(response);
		} catch (Exception e) {
			return ResponseEntity.ok(RestControllerUtil.mostrarErroPadraoObject(response, e.getMessage()));
		}
	}
    
    @GetMapping(value = "field={field}/value={value}")
    public ResponseEntity<Response<Perfil>> findByField(@PathVariable("field") String field, 
    		@PathVariable("value") String value) {
    	return ResponseEntity.ok(RestControllerUtil.findByField(service, field, value));
    }
    
    @GetMapping(value = "/exists/field={field}/value={value}")
    public ResponseEntity<Response<Boolean>> existsByField(@PathVariable("field") String field, 
    		@PathVariable("value") String value) {
    	return ResponseEntity.ok(RestControllerUtil.existsByField(service, field, value));
    }
}
