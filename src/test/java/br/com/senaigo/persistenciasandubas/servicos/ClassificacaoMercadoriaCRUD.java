package br.com.senaigo.persistenciasandubas.servicos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Type;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.http.HttpMethod;

import com.google.gson.reflect.TypeToken;

import br.com.senaigo.persistenciasandubas.model.ClassificacaoMercadoria;
import br.com.senaigo.persistenciasandubas.model.Page;
import br.com.senaigo.persistenciasandubas.response.Response;
import br.com.senaigo.persistenciasandubas.servicos.util.ClientHelp;

@SuppressWarnings("unchecked")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ClassificacaoMercadoriaCRUD {
	
	private static final String URL_ENTIDADE = ClientHelp.URL + "/classificacaomercadoria";
	private Type type = new TypeToken<Response<ClassificacaoMercadoria>>() {}.getType();
	
	//atributos
	private static Long id;
	
	//novo
	private static final String NOME = "teste nome novo";
	private static final String DESCRICAO = "teste descrição novo";
	
	//alteracao
	private static final String NOME_UPDATE = "teste nome alteracao";
	private static final String DESCRICAO_UPDATE = "teste descrição alteracao";

	@Test
	public void test01FindAll() {
		try {
			Response<List<ClassificacaoMercadoria>> response = null;
			List<ClassificacaoMercadoria> objeto = null;
			Type type = new TypeToken<Response<List<ClassificacaoMercadoria>>>() {}.getType();
			response = (Response<List<ClassificacaoMercadoria>>) ClientHelp.metodo(URL_ENTIDADE, HttpMethod.GET, type);
			assertNotNull(response);
			objeto = response.getData();
			assertNotNull(objeto);
			assertEquals(2, objeto.size());
			for (ClassificacaoMercadoria pos : objeto) {
				assertNotNull(pos);
				assertNotNull(pos.getId());
				assertTrue(pos.getId() > 0);
			}
		} catch (Exception e) {
			assertTrue(Boolean.FALSE);
		}
	}
	
	@Test
	public void test02NewObject() {
		try {
			ClassificacaoMercadoria objeto = new ClassificacaoMercadoria(null, NOME, DESCRICAO);
			Response<ClassificacaoMercadoria> response = null;
			response = (Response<ClassificacaoMercadoria>) ClientHelp.metodo(URL_ENTIDADE, HttpMethod.POST, type, objeto);
			assertNotNull(response);
			objeto = response.getData();
			assertNotNull(objeto);
			assertNotNull(objeto.getId());
			assertEquals(NOME, objeto.getNome());
			assertEquals(DESCRICAO, objeto.getDescricao());
			id = objeto.getId();
		} catch (Exception e) {
			assertTrue(Boolean.FALSE);
		}
	}

	@Test
	public void test03FindById() {
		try {
			final String url = URL_ENTIDADE + "/" + id;
			ClassificacaoMercadoria objeto = null;
			Response<ClassificacaoMercadoria> response = null;
			response = (Response<ClassificacaoMercadoria>) ClientHelp.metodo(url, HttpMethod.GET, type);
			assertNotNull(response);
			objeto = response.getData();
			assertNotNull(objeto);
			assertNotNull(objeto.getId());
			assertEquals(NOME, objeto.getNome());
			assertEquals(DESCRICAO, objeto.getDescricao());
		} catch (Exception e) {
			assertTrue(Boolean.FALSE);
		}
	}

	@Test
	public void test04Update() {
		try {
			ClassificacaoMercadoria objeto = new ClassificacaoMercadoria(id, NOME_UPDATE, DESCRICAO_UPDATE);
			Response<ClassificacaoMercadoria> response = null;
			response = (Response<ClassificacaoMercadoria>) ClientHelp.metodo(URL_ENTIDADE, HttpMethod.PUT, type, objeto);
			assertNotNull(response);
			objeto = response.getData();
			assertNotNull(objeto);
			assertNotNull(objeto.getId());
			assertEquals(id, objeto.getId());
			assertEquals(NOME_UPDATE, objeto.getNome());
			assertEquals(DESCRICAO_UPDATE, objeto.getDescricao());
		} catch (Exception e) {
			assertTrue(Boolean.FALSE);
		}
	}
	
	@Test
	public void test05Paginacao() {
		try {
			final String page = "/0";
			final String count = "/5";
			final String idP = "/" + id;
			final String idP2 = "/0";
			final String nome = "/ito";
			final String descricao = "/rodu";
			final String uninformed = "/uninformed";
			
			StringBuilder tudo = new StringBuilder(URL_ENTIDADE);
			tudo.append(page).append(count);
			final String urlComecoPadrao = tudo.toString();
			tudo = new StringBuilder(urlComecoPadrao);
			tudo.append(idP).append(uninformed).append(uninformed);
			final String urlID = tudo.toString();
			tudo = new StringBuilder(urlComecoPadrao);
			tudo.append(idP2).append(nome).append(uninformed);
			final String urlNome = tudo.toString();
			tudo = new StringBuilder(urlComecoPadrao);
			tudo.append(idP2).append(uninformed).append(descricao);
			final String urlDescricao = tudo.toString();
			
			Long idEsperado = new Long(3);
			
			Response<Page<ClassificacaoMercadoria>> response = null;
			Type type = new TypeToken<Response<Page<ClassificacaoMercadoria>>>() {}.getType();
			response = (Response<Page<ClassificacaoMercadoria>>) ClientHelp.metodo(urlID, HttpMethod.GET, type);
			assertNotNull(response);
			assertNotNull(response.getData().getContent());
			assertEquals(id, response.getData().getContent().get(0).getId());
			
			response = (Response<Page<ClassificacaoMercadoria>>) ClientHelp.metodo(urlNome, HttpMethod.GET, type);
			assertNotNull(response);
			assertNotNull(response.getData().getContent());
			assertEquals(idEsperado, response.getData().getContent().get(0).getId());
			
			response = (Response<Page<ClassificacaoMercadoria>>) ClientHelp.metodo(urlDescricao, HttpMethod.GET, type);
			assertNotNull(response);
			assertNotNull(response.getData().getContent());
			assertEquals(idEsperado, response.getData().getContent().get(0).getId());
		} catch (Exception e) {
			assertTrue(Boolean.FALSE);
		}
	}

	@Test
	public void test06DeleteById() {
		try {
			final String url = URL_ENTIDADE + "/" + id;
			Boolean objeto = null;
			Response<Boolean> response = null;
			Type type = new TypeToken<Response<Boolean>>() {}.getType();
			response = (Response<Boolean>) ClientHelp.metodo(url, HttpMethod.DELETE, type);
			assertNotNull(response);
			objeto = response.getData();
			assertNotNull(objeto);
			assertTrue(objeto);
		} catch (Exception e) {
			assertTrue(Boolean.FALSE);
		}
	}
}
