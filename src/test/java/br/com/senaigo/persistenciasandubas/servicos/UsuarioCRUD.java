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

import br.com.senaigo.persistenciasandubas.model.Email;
import br.com.senaigo.persistenciasandubas.model.Page;
import br.com.senaigo.persistenciasandubas.model.Telefone;
import br.com.senaigo.persistenciasandubas.model.Usuario;
import br.com.senaigo.persistenciasandubas.model.enummeration.FuncaoUsuarioEnum;
import br.com.senaigo.persistenciasandubas.model.enummeration.StatusUsuarioEnum;
import br.com.senaigo.persistenciasandubas.model.enummeration.TipoTelefoneEnum;
import br.com.senaigo.persistenciasandubas.response.Response;
import br.com.senaigo.persistenciasandubas.servicos.util.ClientHelp;
import br.com.senaigo.persistenciasandubas.servicos.util.ServicosTestTemplateUtil;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UsuarioCRUD extends ServicosTestTemplateUtil<Usuario>{
	
	private static final String URL_ENTIDADE = ClientHelp.URL + "/usuario";
	private Type type = new TypeToken<Response<Usuario>>() {}.getType();
	
	//atributos
	private static Long id;
	
	//novo
	private static final String NOME = "teste nome novo";
	private static final String SENHA = "teste senha novo";
	private static final String LOGIN = "teste login novo";
	private static final StatusUsuarioEnum STATUS_USUARIO_ENUM = StatusUsuarioEnum.ATIVO;
	private static final FuncaoUsuarioEnum FUNCAO_USUARIO_ENUM = FuncaoUsuarioEnum.FUNCIONARIO;
	private static Email email = new Email(null, "teste@teste.novo", Boolean.TRUE);
	private static Telefone telefone = new Telefone(null, "(62)9 9826 4577", TipoTelefoneEnum.CELULAR);
	
	//alteracao
	private static final String NOME_UPDATE = "teste nome alteracao";
	private static final String SENHA_UPDATE = "teste senha alteracao";
	private static final String LOGIN_UPDATE = "teste login alteracao";
	private static final StatusUsuarioEnum STATUS_USUARIO_ENUM_UPDATE = StatusUsuarioEnum.INATIVO;
	private static final FuncaoUsuarioEnum FUNCAO_USUARIO_ENUM_UPDATE = FuncaoUsuarioEnum.OPERADOR_CAIXA;
	private static Email emailUPDATE = new Email(null, "teste@teste.alteracao", Boolean.TRUE);
	private static Telefone telefoneUPDATE = new Telefone(null, "(62)9 9826 4577", TipoTelefoneEnum.FIXO);

	@Test
	@Override
	public void test01FindAll() {
		Type type = new TypeToken<Response<List<Usuario>>>() {}.getType();
		testFindAll(1, type);
	}

	@Test
	@Override
	public void test02NewObject() {
		try {
			Usuario objeto = new Usuario(SENHA, NOME, LOGIN, STATUS_USUARIO_ENUM, FUNCAO_USUARIO_ENUM, email, telefone);
			objeto = testNewObject(objeto);
			testAtributoEquals(SENHA, NOME, LOGIN, STATUS_USUARIO_ENUM, FUNCAO_USUARIO_ENUM, email, telefone, objeto);
			id = objeto.getId();
			emailUPDATE.setId(objeto.getEmail().getId());
			telefoneUPDATE.setId(objeto.getTelefone().getId());
		} catch (Exception e) {
			assertTrue(Boolean.FALSE);
		}
	}

	@Test
	@Override
	public void test03FindById() {
		try {
			Usuario objeto = testFindById();
			testAtributoEquals(SENHA, NOME, LOGIN, STATUS_USUARIO_ENUM, FUNCAO_USUARIO_ENUM, email, telefone, objeto);
		} catch (Exception e) {
			assertTrue(Boolean.FALSE);
		}
	}

	@Test
	@Override
	public void test04Update() {
		try {
			Usuario objeto = new Usuario(id, SENHA_UPDATE, NOME_UPDATE, LOGIN_UPDATE, STATUS_USUARIO_ENUM_UPDATE, 
					FUNCAO_USUARIO_ENUM_UPDATE, emailUPDATE, telefoneUPDATE);
			objeto = testUpdate(objeto);
			assertEquals(id, objeto.getId());
			testAtributoEquals(SENHA_UPDATE, NOME_UPDATE, LOGIN_UPDATE, STATUS_USUARIO_ENUM_UPDATE, 
					FUNCAO_USUARIO_ENUM_UPDATE, emailUPDATE, telefoneUPDATE, objeto);
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
			final String login = "/oo";
			final String statusUsuarioEnum = "/ATIVO";
			final String funcaoUsuarioEnum = "/ROOT";
			final String uninformed = "/uninformed";
			
			StringBuilder tudo = new StringBuilder(URL_ENTIDADE);
			tudo.append(page).append(count);
			final String urlComecoPadrao = tudo.toString();
			
			tudo = new StringBuilder(urlComecoPadrao);
			tudo.append(idP).append(uninformed).append(uninformed).append(uninformed).append(uninformed);
			final String urlID = tudo.toString();
			
			tudo = new StringBuilder(urlComecoPadrao);
			tudo.append(idP2).append(nome).append(uninformed).append(uninformed).append(uninformed);
			final String urlNome = tudo.toString();
			
			tudo = new StringBuilder(urlComecoPadrao);
			tudo.append(idP2).append(uninformed).append(login).append(uninformed).append(uninformed);
			final String urlLogin = tudo.toString();
			
			tudo = new StringBuilder(urlComecoPadrao);
			tudo.append(idP2).append(uninformed).append(uninformed).append(statusUsuarioEnum).append(uninformed);
			final String urlStatusUsuarioEnum = tudo.toString();
			
			tudo = new StringBuilder(urlComecoPadrao);
			tudo.append(idP2).append(uninformed).append(uninformed).append(uninformed).append(funcaoUsuarioEnum);
			final String urlFuncaoUsuarioEnum = tudo.toString();
			
			Type type = new TypeToken<Response<Page<Usuario>>>() {}.getType();
			Page[] pages = testPaginacao(type, urlID, urlNome, urlLogin, urlStatusUsuarioEnum, urlFuncaoUsuarioEnum);
			Long idEsperado = new Long(18);
			assertEquals(id, ((Usuario) pages[0].getContent().get(0)).getId());
			for (int i = 1; i < pages.length; i++) {
				assertEquals(idEsperado, ((Usuario) pages[i].getContent().get(0)).getId());
			}
		} catch (Exception e) {
			assertTrue(Boolean.FALSE);
		}
	}
	
	@Test
	@Override
	public void test07FindByField() {
		try {
			final String login = "root";
			Usuario objeto = testFindByField("login", login);
			assertEquals(new Long(18), objeto.getId());
		} catch (Exception e) {
			assertTrue(Boolean.FALSE);
		}
	}
	
	@Test
	@Override
	public void test08ExistsByField() {
		try {
			final String login = "root";
			testExistsByField("login", login);
		} catch (Exception e) {
			assertTrue(Boolean.FALSE);
		}
	}
	
	private void testAtributoEquals(String senha, String nome, String login, StatusUsuarioEnum statusUsuarioEnum, 
			FuncaoUsuarioEnum funcaoUsuarioEnum, Email email, Telefone telefone, Usuario objeto) {
		try {
			assertEquals(nome, objeto.getNome());
			assertEquals(senha, objeto.getSenha());
			assertEquals(login, objeto.getLogin());
			assertEquals(statusUsuarioEnum, objeto.getStatusUsuarioEnum());
			assertEquals(funcaoUsuarioEnum, objeto.getFuncaoUsuarioEnum());
			assertEquals(email.getEmail(), objeto.getEmail().getEmail());
			assertEquals(telefone.getNumero(), objeto.getTelefone().getNumero());
			assertEquals(telefone.getTipoTelefoneEnum(), objeto.getTelefone().getTipoTelefoneEnum());
		} catch (Exception e) {
			assertTrue(Boolean.FALSE);
		}
	}

	@Override
	public void testAssertNotNullObjectAndId(Usuario objeto) {
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