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

import br.com.senaigo.persistenciasandubas.model.Telefone;
import br.com.senaigo.persistenciasandubas.model.enummeration.TipoTelefoneEnum;
import br.com.senaigo.persistenciasandubas.response.Response;
import br.com.senaigo.persistenciasandubas.servicos.util.ClientHelp;
import br.com.senaigo.persistenciasandubas.servicos.util.ServicosTestTemplateUtil;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TelefoneCRUD extends ServicosTestTemplateUtil<Telefone>{
	
	private static final String URL_ENTIDADE = ClientHelp.URL + "/telefone";
	private Type type = new TypeToken<Response<Telefone>>() {}.getType();
	
	//atributos
	private static Long id;
	
	//novo
	private static final String NUMERO = "62 9 9826 4577";
	private static final TipoTelefoneEnum TIPO_TELEFONE_ENUM = TipoTelefoneEnum.CELULAR;
	
	//alteracao
	private static final String NUMERO_UPDATE = "62 9 9449 8273";
	private static final TipoTelefoneEnum TIPO_TELEFONE_ENUM_UPDATE = TipoTelefoneEnum.FIXO;

	@Test
	@Override
	public void test01FindAll() {
		Type type = new TypeToken<Response<List<Telefone>>>() {}.getType();
		testFindAll(4, type);
	}

	@Test
	@Override
	public void test02NewObject() {
		try {
			Telefone objeto = new Telefone(null, NUMERO, TIPO_TELEFONE_ENUM);
			objeto = testNewObject(objeto);
			testAtributoEquals(NUMERO, TIPO_TELEFONE_ENUM, objeto);
			id = objeto.getId();
		} catch (Exception e) {
			assertTrue(Boolean.FALSE);
		}
	}

	@Test
	@Override
	public void test03FindById() {
		try {
			Telefone objeto = testFindById();
			testAtributoEquals(NUMERO, TIPO_TELEFONE_ENUM, objeto);
		} catch (Exception e) {
			assertTrue(Boolean.FALSE);
		}
	}

	@Test
	@Override
	public void test04Update() {
		try {
			Telefone objeto = new Telefone(id, NUMERO_UPDATE, TIPO_TELEFONE_ENUM_UPDATE);
			objeto = testUpdate(objeto);
			assertEquals(id, objeto.getId());
			testAtributoEquals(NUMERO_UPDATE, TIPO_TELEFONE_ENUM_UPDATE, objeto);
		} catch (Exception e) {
			assertTrue(Boolean.FALSE);
		}
	}

	@Override
	public void test05Paginacao() {}
	
	@Test
	@Override
	public void test07FindByField() {
		try {
			final String numero = "(62)998264577";
			Telefone objeto = testFindByField("numero", numero);
			testAtributoEquals("(62)998264577", TipoTelefoneEnum.CELULAR, objeto);
		} catch (Exception e) {
			assertTrue(Boolean.FALSE);
		}
	}
	
	@Test
	@Override
	public void test08ExistsByField() {
		try {
			final String numero = "(62)998264577";
			testExistsByField("numero", numero);
		} catch (Exception e) {
			assertTrue(Boolean.FALSE);
		}
	}
	
	private void testAtributoEquals(String numero, TipoTelefoneEnum tipoTelefoneEnum, Telefone objeto) {
		assertEquals(numero, objeto.getNumero());
		assertEquals(tipoTelefoneEnum, objeto.getTipoTelefoneEnum());
	}

	@Override
	public void testAssertNotNullObjectAndId(Telefone objeto) {
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
