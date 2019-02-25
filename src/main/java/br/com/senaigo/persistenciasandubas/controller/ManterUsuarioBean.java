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

import br.com.senaigo.persistenciasandubas.model.Usuario;
import br.com.senaigo.persistenciasandubas.response.Response;
import br.com.senaigo.persistenciasandubas.service.UsuarioService;
import br.com.senaigo.persistenciasandubas.util.RestControllerUtil;

@RestController
@RequestMapping("/usuario")
@CrossOrigin(origins = "*")
public class ManterUsuarioBean {

	@Autowired
    private UsuarioService service;

	@GetMapping
    public ResponseEntity<Response<List<Usuario>>> findAll() {
        return ResponseEntity.ok(RestControllerUtil.findAll(service));
    }
 
    @GetMapping(value = "{id}")
    public ResponseEntity<Response<Usuario>> findById(@PathVariable("id") String id) {
    	return ResponseEntity.ok(RestControllerUtil.findById(service, id));
    }
 
    @PostMapping
    public ResponseEntity<Response<Usuario>> newObject(@RequestBody Usuario objeto) {
    	return ResponseEntity.ok(RestControllerUtil.save(service, objeto));
    }
 
    @PutMapping
    public ResponseEntity<Response<Usuario>> update(@RequestBody Usuario objeto) {
    	return ResponseEntity.ok(RestControllerUtil.save(service, objeto));
    }
 
    @DeleteMapping(value = "{id}")
    public ResponseEntity<Response<Boolean>> deleteById(@PathVariable("id") String id) {
    	return ResponseEntity.ok(RestControllerUtil.deleteById(service, id));
    }
    
    @GetMapping(value = "{page}/{count}/{id}/{nome}/{login}/{statusUsuarioEnum}/{funcaoUsuarioEnum}")
	public ResponseEntity<Response<Page<Usuario>>> findByParams(@PathVariable("page") Integer page,
			@PathVariable("count") Integer count, @PathVariable("id") Long id, @PathVariable("nome") String nome, 
			@PathVariable("login") String login, @PathVariable("statusUsuarioEnum") String statusUsuarioEnum, 
			@PathVariable("funcaoUsuarioEnum") String funcaoUsuarioEnum) {
    	Response<Page<Usuario>> response = new Response<>();
    	try {
    		Page<Usuario> pagina = service.paginarComParemetros(page, count, id, nome, login, statusUsuarioEnum, funcaoUsuarioEnum);
    		response.setData(pagina);
    		return ResponseEntity.ok(response);
		} catch (Exception e) {
			return ResponseEntity.ok(RestControllerUtil.mostrarErroPadraoObject(response, e.getMessage()));
		}
	}
    
    @GetMapping(value = "field={field}/value={value}")
    public ResponseEntity<Response<Usuario>> findByField(@PathVariable("field") String field, 
    		@PathVariable("value") String value) {
    	return ResponseEntity.ok(RestControllerUtil.findByField(service, field, value));
    }
    
    @GetMapping(value = "/exists/field={field}/value={value}")
    public ResponseEntity<Response<Boolean>> existsByField(@PathVariable("field") String field, 
    		@PathVariable("value") String value) {
    	return ResponseEntity.ok(RestControllerUtil.existsByField(service, field, value));
    }
    
    @GetMapping(value = "/inativar={id}")
    public ResponseEntity<Response<Usuario>> inativarUsuarioById(@PathVariable("id") String id) {
    	Response<Usuario> response = new Response<>();
		try {
	        Usuario objeto = service.inativarUsuarioById(id);
	        response.setData(objeto);
	        return ResponseEntity.ok(response);
		} catch (Exception e) {
			return ResponseEntity.ok(RestControllerUtil.mostrarErroPadraoObject(response, e.getMessage()));
		}
    }
}
