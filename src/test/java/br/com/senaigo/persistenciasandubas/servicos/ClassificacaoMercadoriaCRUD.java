package br.com.senaigo.persistenciasandubas.servicos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.lang.reflect.Type;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.http.HttpMethod;

import com.google.gson.reflect.TypeToken;

import br.com.senaigo.persistenciasandubas.model.ClassificacaoMercadoria;
import br.com.senaigo.persistenciasandubas.response.Response;
import br.com.senaigo.persistenciasandubas.servicos.util.ClientHelp;

@SuppressWarnings("unchecked")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ClassificacaoMercadoriaCRUD {
	
	private static final String URL_ENTIDADE = ClientHelp.URL + "/classificacaomercadoria";
	private Long id;

	@Test
	public void test01FindAll() {
		Response<List<ClassificacaoMercadoria>> response = null;
		List<ClassificacaoMercadoria> objeto = null;
		Type type = new TypeToken<Response<List<ClassificacaoMercadoria>>>() {}.getType();
		response = (Response<List<ClassificacaoMercadoria>>) ClientHelp.metodo(URL_ENTIDADE, HttpMethod.GET, type);
		objeto = response.getData();
		assertEquals(2, objeto.size());
	}
	
	@Test
	public void test02NewObject() {
		try {
			final String nome = "teste nome";
			final String descricao = "teste descrição";
			ClassificacaoMercadoria objeto = new ClassificacaoMercadoria(null, nome, descricao);
			Response<ClassificacaoMercadoria> response = null;
			Type type = new TypeToken<Response<ClassificacaoMercadoria>>() {}.getType();
			response = (Response<ClassificacaoMercadoria>) ClientHelp.metodo(URL_ENTIDADE, HttpMethod.POST, type, objeto);
			objeto = response.getData();
			assertNotNull(objeto);
			assertNotNull(objeto.getId());
			assertEquals(nome, objeto.getNome());
			assertEquals(descricao, objeto.getDescricao());
			this.id = objeto.getId();
		} catch (Exception e) {
			assertTrue(Boolean.FALSE);
		}
	}

	@Test
	public void test03FindById() {
		fail("Not yet implemented");
	}

	@Test
	public void test04Update() {
		fail("Not yet implemented");
	}

	@Test
	public void test05DeleteById() {
		fail("Not yet implemented");
	}
}
