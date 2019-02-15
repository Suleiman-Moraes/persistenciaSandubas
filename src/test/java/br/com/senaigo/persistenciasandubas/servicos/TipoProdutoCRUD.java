package br.com.senaigo.persistenciasandubas.servicos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Type;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.google.gson.reflect.TypeToken;

import br.com.senaigo.persistenciasandubas.model.Page;
import br.com.senaigo.persistenciasandubas.model.TipoProduto;
import br.com.senaigo.persistenciasandubas.response.Response;
import br.com.senaigo.persistenciasandubas.servicos.util.ClientHelp;
import br.com.senaigo.persistenciasandubas.servicos.util.ServicosTestTemplateUtil;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TipoProdutoCRUD extends ServicosTestTemplateUtil<TipoProduto>{
	
	private static final String URL_ENTIDADE = ClientHelp.URL + "/tipoproduto";
	private Type type = new TypeToken<Response<TipoProduto>>() {}.getType();
	
	//atributos
	private static Long id;
	
	//novo
	private static final String NOME = "teste nome novo";
	private static final String DESCRICAO = "teste descrição novo";
	
	//alteracao
	private static final String NOME_UPDATE = "teste nome alteracao";
	private static final String DESCRICAO_UPDATE = "teste descrição alteracao";

	@Test
	@Override
	public void test01FindAll() {
		Type type = new TypeToken<Response<List<TipoProduto>>>() {}.getType();
		testFindAll(2, type);
	}

	@Test
	@Override
	public void test02NewObject() {
		try {
			TipoProduto objeto = new TipoProduto(null, NOME, DESCRICAO);
			objeto = testNewObject(objeto);
			testAtributoEquals(NOME, DESCRICAO, objeto);
			id = objeto.getId();
		} catch (Exception e) {
			assertTrue(Boolean.FALSE);
		}
	}

	@Test
	@Override
	public void test03FindById() {
		try {
			TipoProduto objeto = testFindById();
			testAtributoEquals(NOME, DESCRICAO, objeto);
		} catch (Exception e) {
			assertTrue(Boolean.FALSE);
		}
	}

	@Test
	@Override
	public void test04Update() {
		try {
			TipoProduto objeto = new TipoProduto(id, NOME_UPDATE, DESCRICAO_UPDATE);
			objeto = testUpdate(objeto);
			assertEquals(id, objeto.getId());
			testAtributoEquals(NOME_UPDATE, DESCRICAO_UPDATE, objeto);
		} catch (Exception e) {
			assertTrue(Boolean.FALSE);
		}
	}

	@SuppressWarnings("rawtypes")
	@Test
	@Override
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
			
			Type type = new TypeToken<Response<Page<TipoProduto>>>() {}.getType();
			Page[] pages = testPaginacao(type, urlID, urlNome, urlDescricao);
			Long idEsperado = new Long(3);
			assertEquals(id, ((TipoProduto) pages[0].getContent().get(0)).getId());
			for (int i = 1; i < pages.length; i++) {
				assertEquals(idEsperado, ((TipoProduto) pages[i].getContent().get(0)).getId());
			}
		} catch (Exception e) {
			assertTrue(Boolean.FALSE);
		}
	}
	
	@Override
	public void test07FindByField() {
		try {
			final String nome = "Refrigerante baitola";
			TipoProduto objeto = testFindByField("nome", nome);
			testAtributoEquals(nome, "Produtos prontos para venda", objeto);
		} catch (Exception e) {
			assertTrue(Boolean.FALSE);
		}
	}
	
	private void testAtributoEquals(String nome, String descricao, TipoProduto objeto) {
		assertEquals(nome, objeto.getNome());
		assertEquals(descricao, objeto.getDescricao());
	}

	@Override
	public void testAssertNotNullObjectAndId(TipoProduto objeto) {
		assertNotNull(objeto);
		assertNotNull(objeto.getId());
		assertTrue(objeto.getId() > 0);
	}

	@Override
	public String getURL_ENTIDADE() {
		return URL_ENTIDADE;
	}

	@Override
	public String getId() {
		return id + "";
	}

	@Override
	public Type getType() {
		return type;
	}
}
