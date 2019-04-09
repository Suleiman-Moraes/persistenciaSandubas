package br.com.senaigo.persistenciasandubas.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.senaigo.persistenciasandubas.model.UserMobile;
import br.com.senaigo.persistenciasandubas.response.Response;
import br.com.senaigo.persistenciasandubas.util.RestControllerUtil;
import br.com.senaigo.persistenciasandubas.util.StringUtil;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class ManterUserBean {
	
	@GetMapping(value = "/login")
    public ResponseEntity<Response<UserMobile>> login(@RequestParam("login")String login, 
    		@RequestParam("senha")String senha) {
		Response<UserMobile> response = new Response<>();
		try {
			if(StringUtil.isNotNullOrEmpity(login) && login.equals("susu") 
					&& StringUtil.isNotNullOrEmpity(senha) && senha.equals("123456")) {
				response.setData(new UserMobile(new Long("1"), senha, "Suleiman", login, "suleimanmoraes@gmail.com", "202.153.570-35"));
			}
			else {
				throw new Exception("Login ou senha incorretos.");
			}
    		return ResponseEntity.ok(response);
		} catch (Exception e) {
			return ResponseEntity.ok(RestControllerUtil.mostrarErroPadraoObject(response, e.getMessage()));
		}
    }
}
