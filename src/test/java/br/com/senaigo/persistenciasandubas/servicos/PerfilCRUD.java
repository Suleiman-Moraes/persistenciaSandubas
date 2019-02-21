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
import br.com.senaigo.persistenciasandubas.model.Perfil;
import br.com.senaigo.persistenciasandubas.response.Response;
import br.com.senaigo.persistenciasandubas.servicos.util.ClientHelp;
import br.com.senaigo.persistenciasandubas.servicos.util.ServicosTestTemplateUtil;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PerfilCRUD extends ServicosTestTemplateUtil<Perfil>{
	
	private static final String URL_ENTIDADE = ClientHelp.URL + "/perfil";
	private Type type = new TypeToken<Response<Perfil>>() {}.getType();
	
	//atributos
	private static Long id;
	private Funcionalidade funcionalidade = new Funcionalidade(1l, "Atualizar Minhas Informações");
	
	//novo
	private static final String NOME = "teste nome novo";
	private static final String DESCRICAO = "teste descrição novo";
	
	//alteracao
	private static final String NOME_UPDATE = "teste nome alteracao";
	private static final String DESCRICAO_UPDATE = "teste descrição alteracao";

	@Test
	@Override
	public void test01FindAll() {
		Type type = new TypeToken<Response<List<Perfil>>>() {}.getType();
		testFindAll(4, type);
	}

	@Test
	@Override
	public void test02NewObject() {
		try {
			Perfil objeto = new Perfil(null, NOME, DESCRICAO, "INTERNO", funcionalidade);
			objeto = testNewObject(objeto);
			testAtributoEquals(NOME, DESCRICAO, funcionalidade, objeto);
			id = objeto.getId();
		} catch (Exception e) {
			assertTrue(Boolean.FALSE);
		}
	}

	@Test
	@Override
	public void test03FindById() {
		try {
			Perfil objeto = testFindById();
			testAtributoEquals(NOME, DESCRICAO, funcionalidade, objeto);
		} catch (Exception e) {
			assertTrue(Boolean.FALSE);
		}
	}

	@Test
	@Override
	public void test04Update() {
		try {
			Perfil objeto = new Perfil(id, NOME_UPDATE, DESCRICAO_UPDATE, "INTERNO", funcionalidade);
			objeto = testUpdate(objeto);
			assertEquals(id, objeto.getId());
			testAtributoEquals(NOME_UPDATE, DESCRICAO_UPDATE, funcionalidade, objeto);
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
			final String nome = "/oo";
			final String descricao = "/PRE";
			final String funcionalidadeId = "/1";
			final String uninformed = "/uninformed";
			
			StringBuilder tudo = new StringBuilder(URL_ENTIDADE);
			tudo.append(page).append(count);
			final String urlComecoPadrao = tudo.toString();
			
			tudo = new StringBuilder(urlComecoPadrao);
			tudo.append(idP).append(uninformed).append(uninformed).append(idP2);
			final String urlID = tudo.toString();
			
			tudo = new StringBuilder(urlComecoPadrao);
			tudo.append(idP2).append(nome).append(uninformed).append(idP2);
			final String urlNome = tudo.toString();
			
			tudo = new StringBuilder(urlComecoPadrao);
			tudo.append(idP2).append(uninformed).append(descricao).append(idP2);
			final String urlDescricao = tudo.toString();
			
			tudo = new StringBuilder(urlComecoPadrao);
			tudo.append(idP2).append(uninformed).append(uninformed).append(funcionalidadeId);
			final String urlFuncionalidade = tudo.toString();
			
			Type type = new TypeToken<Response<Page<Perfil>>>() {}.getType();
			Page[] pages = testPaginacao(type, urlID, urlNome, urlDescricao, urlFuncionalidade);
			Long idEsperado = new Long(1);
			assertEquals(id, ((Perfil) pages[0].getContent().get(0)).getId());
			for (int i = 1; i < pages.length - 1; i++) {
				assertEquals(idEsperado, ((Perfil) pages[i].getContent().get(0)).getId());
			}
			assertEquals(idEsperado, ((Perfil) pages[3].getContent().get(2)).getId());
			assertEquals(3, pages[3].getContent().size());
		} catch (Exception e) {
			assertTrue(Boolean.FALSE);
		}
	}
	
	@Test
	@Override
	public void test07FindByField() {
		try {
			final String descricao = "USUÁRIO SUPREMO DO SISTEMA";
			Perfil objeto = testFindByField("descricao", descricao);
			assertEquals(new Long(1), objeto.getId());
		} catch (Exception e) {
			assertTrue(Boolean.FALSE);
		}
	}
	
	@Test
	@Override
	public void test08ExistsByField() {
		try {
			final String descricao = "USUÁRIO SUPREMO DO SISTEMA";
			testExistsByField("descricao", descricao);
		} catch (Exception e) {
			assertTrue(Boolean.FALSE);
		}
	}
	
	private void testAtributoEquals(String nome, String descricao, Funcionalidade funcionalidade, Perfil objeto) {
		try {
			assertEquals(nome, objeto.getNome());
			assertEquals(descricao, objeto.getDescricao());
			assertEquals(funcionalidade, objeto.getFuncionalidades().get(0));
		} catch (Exception e) {
			assertTrue(Boolean.FALSE);
		}
	}

	@Override
	public void testAssertNotNullObjectAndId(Perfil objeto) {
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
