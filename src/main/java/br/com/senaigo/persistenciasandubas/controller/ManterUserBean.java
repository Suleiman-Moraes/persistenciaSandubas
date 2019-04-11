package br.com.senaigo.persistenciasandubas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.senaigo.persistenciasandubas.model.User;
import br.com.senaigo.persistenciasandubas.response.Response;
import br.com.senaigo.persistenciasandubas.service.UserService;
import br.com.senaigo.persistenciasandubas.util.RestControllerUtil;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class ManterUserBean {
	
	@Autowired
	private UserService service;
	
	@GetMapping(value = "/login")
	public ResponseEntity<Response<User>> login(@RequestParam("login")String login, 
			@RequestParam("senha")String senha) {
		Response<User> response = new Response<>();
		try {
			response.setData(service.login(login, senha));
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			return ResponseEntity.ok(RestControllerUtil.mostrarErroPadraoObject(response, e.getMessage()));
		}
	}
	
	@PostMapping
	public ResponseEntity<Response<User>> novoUser(@RequestBody User objeto) {
		Response<User> response = new Response<>();
		try {
			response.setData(service.save(objeto));
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			return ResponseEntity.ok(RestControllerUtil.mostrarErroPadraoObject(response, e.getMessage()));
		}
	}
	
	@GetMapping
    public List<User> novoUserx() {
		return service.findAll();
    }
}
