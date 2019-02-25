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
import br.com.senaigo.persistenciasandubas.response.Response;
import br.com.senaigo.persistenciasandubas.servicos.util.ClientHelp;
import br.com.senaigo.persistenciasandubas.servicos.util.ServicosTestTemplateUtil;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EmailCRUD extends ServicosTestTemplateUtil<Email>{
	
	private static final String URL_ENTIDADE = ClientHelp.URL + "/email";
	private Type type = new TypeToken<Response<Email>>() {}.getType();
	
	//atributos
	private static Long id;
	
	//novo
	private static final String EMAIL = "suleimanmoraes@yahoo.com";
	
	//alteracao
	private static final String EMAIL_UPDATE = "suleimanmoraes@gmail.com";

	@Test
	@Override
	public void test01FindAll() {
		Type type = new TypeToken<Response<List<Email>>>() {}.getType();
		testFindAll(4, type);
	}

	@Test
	@Override
	public void test02NewObject() {
		try {
			Email objeto = new Email(null, EMAIL, Boolean.FALSE);
			objeto = testNewObject(objeto);
			testAtributoEquals(EMAIL, objeto);
			id = objeto.getId();
		} catch (Exception e) {
			assertTrue(Boolean.FALSE);
		}
	}

	@Test
	@Override
	public void test03FindById() {
		try {
			Email objeto = testFindById();
			testAtributoEquals(EMAIL, objeto);
		} catch (Exception e) {
			assertTrue(Boolean.FALSE);
		}
	}

	@Test
	@Override
	public void test04Update() {
		try {
			Email objeto = new Email(id, EMAIL_UPDATE, Boolean.FALSE);
			objeto = testUpdate(objeto);
			assertEquals(id, objeto.getId());
			testAtributoEquals(EMAIL_UPDATE, objeto);
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
			final String email = "val@val.val";
			Email objeto = testFindByField("email", email);
			testAtributoEquals("val@val.val", objeto);
		} catch (Exception e) {
			assertTrue(Boolean.FALSE);
		}
	}
	
	@Test
	@Override
	public void test08ExistsByField() {
		try {
			final String email = "val@val.val";
			testExistsByField("email", email);
		} catch (Exception e) {
			assertTrue(Boolean.FALSE);
		}
	}
	
	private void testAtributoEquals(String email, Email objeto) {
		assertEquals(email, objeto.getEmail());
	}

	@Override
	public void testAssertNotNullObjectAndId(Email objeto) {
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
