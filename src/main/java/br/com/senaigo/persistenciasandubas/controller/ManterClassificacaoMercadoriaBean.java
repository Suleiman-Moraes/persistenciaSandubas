package br.com.senaigo.persistenciasandubas.controller;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.senaigo.persistenciasandubas.model.ClassificacaoMercadoria;
import br.com.senaigo.persistenciasandubas.response.Response;
import br.com.senaigo.persistenciasandubas.service.ClassificacaoMercadoriaService;

@RestController
@RequestMapping("/classificacaomercadoria")
@CrossOrigin(origins = "*")
public class ManterClassificacaoMercadoriaBean {

	@Autowired
    private ClassificacaoMercadoriaService service;

	@RequestMapping("/")
	@ResponseBody
	public String welcome() {
		return "Persistencia Classificação Mercadoria";
	}
	
	@GetMapping
    public ResponseEntity<Response<List<ClassificacaoMercadoria>>> getListaClassificacaoMercadoria() {
		Response<List<ClassificacaoMercadoria>> response = new Response<>();
        List<ClassificacaoMercadoria> listClassificacaoMercadoria = service.findAll();
        response.setData(listClassificacaoMercadoria);
        return ResponseEntity.ok(response);
    }
 
    @GetMapping(value = "{id}")
    public ResponseEntity<Response<ClassificacaoMercadoria>> getClassificacaoMercadoria(@PathVariable("id") String id) {
    	Response<ClassificacaoMercadoria> response = new Response<>();
    	try {
    		ClassificacaoMercadoria objeto = this.service.findById(id);
    		response.setData(objeto);
    		return ResponseEntity.ok(response);
		} catch (Exception e) {
			response.setData(null);
			List<String> listErro = new ArrayList<>();
			listErro.add(e.getMessage());
			response.setErros(listErro);
			return ResponseEntity.ok(response);
		}
    }
 
    @PostMapping
    public ResponseEntity<Response<ClassificacaoMercadoria>> addClassificacaoMercadoria(@RequestBody ClassificacaoMercadoria objeto) {
    	Response<ClassificacaoMercadoria> response = new Response<>();
    	try {
    		ClassificacaoMercadoria objetoNovo = service.save(objeto);
    		response.setData(objetoNovo);
    		return ResponseEntity.ok(response); 
		} catch (Exception e) {
			response.setData(null);
			List<String> listErro = new ArrayList<>();
			listErro.add(e.getMessage());
			response.setErros(listErro);
			return ResponseEntity.ok(response);
		}
    }
 
    @PutMapping
    public ResponseEntity<Response<ClassificacaoMercadoria>> updateClassificacaoMercadoria(@RequestBody ClassificacaoMercadoria objeto) {
    	Response<ClassificacaoMercadoria> response = new Response<>();
    	try {
    		ClassificacaoMercadoria objetoNovo = service.save(objeto);
    		response.setData(objetoNovo);
    		return ResponseEntity.ok(response); 
		} catch (Exception e) {
			response.setData(null);
			List<String> listErro = new ArrayList<>();
			listErro.add(e.getMessage());
			response.setErros(listErro);
			return ResponseEntity.ok(response);
		}
    }
 
    @DeleteMapping(value = "{id}")
    public ResponseEntity<Response<Boolean>> deleteClassificacaoMercadoria(@PathVariable("id") String id) {
    	Response<Boolean> response = new Response<>();
    	try {
    		Boolean retorno = service.deleteById(id);
			response.setData(retorno);
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			response.setData(Boolean.FALSE);
			List<String> listErro = new ArrayList<>();
			listErro.add(e.getMessage());
			response.setErros(listErro);
			return ResponseEntity.ok(response);
		}
    }
}
