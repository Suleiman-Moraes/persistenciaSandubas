package br.com.senaigo.persistenciasandubas.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.senaigo.persistenciasandubas.model.ClassificacaoMercadoria;
import br.com.senaigo.persistenciasandubas.persistencia.ClassificacaoMercadoriaDAO;

@RestController
public class ManterClassificacaoMercadoriaBean {

	@Autowired
    private ClassificacaoMercadoriaDAO repositorio;

	@RequestMapping("/")
	@ResponseBody
	public String welcome() {
		return "Persistencia Classificação Mercadoria";
	}
	
	// URL:
    // http://localhost:8080/SomeContextPath/listaclassificacaomercadoria
    // http://localhost:8080/SomeContextPath/listaclassificacaomercadoria.xml
    // http://localhost:8080/SomeContextPath/listaclassificacaomercadoria.json
    @RequestMapping(value = "/listaclassificacaomercadoria", //
            method = RequestMethod.GET, //
            produces = { MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public List<ClassificacaoMercadoria> getListaClassificacaoMercadoria() {
        List<ClassificacaoMercadoria> list = repositorio.findAll();
        return list;
    }
 
    // URL:
    // http://localhost:8080/SomeContextPath/classificacaomercadoria/{empNo}
    // http://localhost:8080/SomeContextPath/classificacaomercadoria/{empNo}.xml
    // http://localhost:8080/SomeContextPath/classificacaomercadoria/{empNo}.json
    @SuppressWarnings("unchecked")
	@RequestMapping(value = "/classificacaomercadoria/{empNo}", //
            method = RequestMethod.GET, //
            produces = { MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public ResponseEntity<?> getClassificacaoMercadoria(@PathVariable("empNo") String id) {
    	try {
    		Optional<?> op = this.repositorio.findById(new Long(id));
    		return (ResponseEntity<ClassificacaoMercadoria>) ResponseEntity.ok().body(op.get());
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
    }
 
    // URL:
    // http://localhost:8080/SomeContextPath/classificacaomercadoria
    // http://localhost:8080/SomeContextPath/classificacaomercadoria.xml
    // http://localhost:8080/SomeContextPath/classificacaomercadoria.json
    @RequestMapping(value = "/classificacaomercadoria", //
            method = RequestMethod.POST, //
            produces = { MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public ClassificacaoMercadoria addClassificacaoMercadoria(@RequestBody ClassificacaoMercadoria objeto) {
    	try {
			repositorio.save(objeto);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return objeto;
    }
 
    // URL:
    // http://localhost:8080/SomeContextPath/classificacaomercadoria
    // http://localhost:8080/SomeContextPath/classificacaomercadoria.xml
    // http://localhost:8080/SomeContextPath/classificacaomercadoria.json
    @RequestMapping(value = "/classificacaomercadoria", //
            method = RequestMethod.PUT, //
            produces = { MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public ClassificacaoMercadoria updateClassificacaoMercadoria(@RequestBody ClassificacaoMercadoria objeto) {
    	try {
			repositorio.update(objeto);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return objeto;
    }
 
    // URL:
    // http://localhost:8080/SomeContextPath/classificacaomercadoria/{empNo}
    @RequestMapping(value = "/classificacaomercadoria/{id}", //
            method = RequestMethod.DELETE, //
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public void deleteClassificacaoMercadoria(@PathVariable("id") String id) {
    	try {
			repositorio.deleteById(new Long(id));
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}
