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

import br.com.senaigo.persistenciasandubas.model.TipoProduto;
import br.com.senaigo.persistenciasandubas.repository.TipoProdutos;

@RestController
public class ManterTipoProdutoBean {

	@Autowired
	private TipoProdutos repositorio;

	// URL:
	// http://localhost:8080/SomeContextPath/listatipoproduto
	// http://localhost:8080/SomeContextPath/listatipoproduto.xml
	// http://localhost:8080/SomeContextPath/listatipoproduto.json
	@RequestMapping(value = "/listatipoproduto", //
			method = RequestMethod.GET, //
			produces = { MediaType.APPLICATION_JSON_VALUE, //
					MediaType.APPLICATION_XML_VALUE })
	@ResponseBody
	public List<TipoProduto> getListaTipoProduto() {
		List<TipoProduto> list = repositorio.findAll();
		return list;
	}

	// URL:
	// http://localhost:8080/SomeContextPath/tipoproduto/{empNo}
	// http://localhost:8080/SomeContextPath/tipoproduto/{empNo}.xml
	// http://localhost:8080/SomeContextPath/tipoproduto/{empNo}.json
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/tipoproduto/{empNo}", //
			method = RequestMethod.GET, //
			produces = { MediaType.APPLICATION_JSON_VALUE, //
					MediaType.APPLICATION_XML_VALUE })
	@ResponseBody
	public ResponseEntity<?> getTipoProduto(@PathVariable("empNo") String id) {
		try {
			Optional<?> op = this.repositorio.findById(new Long(id));
			return (ResponseEntity<TipoProduto>) ResponseEntity.ok().body(op.get());
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	// URL:
	// http://localhost:8080/SomeContextPath/tipoproduto
	// http://localhost:8080/SomeContextPath/tipoproduto.xml
	// http://localhost:8080/SomeContextPath/tipoproduto.json
	@RequestMapping(value = "/tipoproduto", //
			method = RequestMethod.POST, //
			produces = { MediaType.APPLICATION_JSON_VALUE, //
					MediaType.APPLICATION_XML_VALUE })
	@ResponseBody
	public TipoProduto addTipoProduto(@RequestBody TipoProduto objeto) {
		try {
			repositorio.save(objeto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return objeto;
	}

	// URL:
	// http://localhost:8080/SomeContextPath/tipoproduto
	// http://localhost:8080/SomeContextPath/tipoproduto.xml
	// http://localhost:8080/SomeContextPath/tipoproduto.json
	@RequestMapping(value = "/tipoproduto", //
			method = RequestMethod.PUT, //
			produces = { MediaType.APPLICATION_JSON_VALUE, //
					MediaType.APPLICATION_XML_VALUE })
	@ResponseBody
	public TipoProduto updateTipoProduto(@RequestBody TipoProduto objeto) {
		try {
			return repositorio.save(objeto);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// URL:
	// http://localhost:8080/SomeContextPath/tipoproduto/{empNo}
	@RequestMapping(value = "/tipoproduto/{id}", //
			method = RequestMethod.DELETE, //
			produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	@ResponseBody
	public void deleteTipoProduto(@PathVariable("id") String id) {
		try {
			repositorio.deleteById(new Long(id));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
