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

import br.com.senaigo.persistenciasandubas.model.Funcionalidade;
import br.com.senaigo.persistenciasandubas.model.Page;
import br.com.senaigo.persistenciasandubas.response.Response;
import br.com.senaigo.persistenciasandubas.servicos.util.ClientHelp;
import br.com.senaigo.persistenciasandubas.servicos.util.ServicosTestTemplateUtil;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FuncionalidadeCRUD extends ServicosTestTemplateUtil<Funcionalidade>{
	
	private static final String URL_ENTIDADE = ClientHelp.URL + "/funcionalidade";
	private Type type = new TypeToken<Response<Funcionalidade>>() {}.getType();
	
	//atributos
	private static Long id;
	
	//novo
	private static final String DESCRICAO = "teste descrição novo";
	
	//alteracao
	private static final String DESCRICAO_UPDATE = "teste descrição alteracao";

	@Test
	@Override
	public void test01FindAll() {
		Type type = new TypeToken<Response<List<Funcionalidade>>>() {}.getType();
		testFindAll(3, type);
	}

	@Test
	@Override
	public void test02NewObject() {
		try {
			Funcionalidade objeto = new Funcionalidade(null, DESCRICAO);
			objeto = testNewObject(objeto);
			testAtributoEquals(DESCRICAO, objeto);
			id = objeto.getId();
		} catch (Exception e) {
			assertTrue(Boolean.FALSE);
		}
	}

	@Test
	@Override
	public void test03FindById() {
		try {
			Funcionalidade objeto = testFindById();
			testAtributoEquals(DESCRICAO, objeto);
		} catch (Exception e) {
			assertTrue(Boolean.FALSE);
		}
	}

	@Test
	@Override
	public void test04Update() {
		try {
			Funcionalidade objeto = new Funcionalidade(id, DESCRICAO_UPDATE);
			objeto = testUpdate(objeto);
			assertEquals(id, objeto.getId());
			testAtributoEquals(DESCRICAO_UPDATE, objeto);
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
			final String descricao = "/nfo";
			final String uninformed = "/uninformed";
			
			StringBuilder tudo = new StringBuilder(URL_ENTIDADE);
			tudo.append(page).append(count);
			final String urlComecoPadrao = tudo.toString();
			
			tudo = new StringBuilder(urlComecoPadrao);
			tudo.append(idP).append(uninformed);
			final String urlID = tudo.toString();
			
			tudo = new StringBuilder(urlComecoPadrao);
			tudo.append(idP2).append(descricao);
			final String urlDescricao = tudo.toString();
			
			Type type = new TypeToken<Response<Page<Funcionalidade>>>() {}.getType();
			Page[] pages = testPaginacao(type, urlID, urlDescricao);
			Long idEsperado = new Long(1);
			assertEquals(id, ((Funcionalidade) pages[0].getContent().get(0)).getId());
			for (int i = 1; i < pages.length; i++) {
				assertEquals(idEsperado, ((Funcionalidade) pages[i].getContent().get(0)).getId());
			}
		} catch (Exception e) {
			assertTrue(Boolean.FALSE);
		}
	}
	
	@Test
	@Override
	public void test07FindByField() {
		try {
			final String descricao = "Atualizar Minhas Informações";
			Funcionalidade objeto = testFindByField("descricao", descricao);
			testAtributoEquals("Atualizar Minhas Informações", objeto);
		} catch (Exception e) {
			assertTrue(Boolean.FALSE);
		}
	}
	
	@Test
	@Override
	public void test08ExistsByField() {
		try {
			final String descricao = "Atualizar Minhas Informações";
			testExistsByField("descricao", descricao);
		} catch (Exception e) {
			assertTrue(Boolean.FALSE);
		}
	}
	
	private void testAtributoEquals(String descricao, Funcionalidade objeto) {
		assertEquals(descricao, objeto.getDescricao());
	}

	@Override
	public void testAssertNotNullObjectAndId(Funcionalidade objeto) {
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
