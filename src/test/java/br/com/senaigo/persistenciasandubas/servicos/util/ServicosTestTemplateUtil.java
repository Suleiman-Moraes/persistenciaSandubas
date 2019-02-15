package br.com.senaigo.persistenciasandubas.servicos.util;

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

import br.com.senaigo.persistenciasandubas.model.Page;
import br.com.senaigo.persistenciasandubas.response.Response;
import br.com.senaigo.persistenciasandubas.servicos.util.iterfaces.ServicosTestTemplateUtilIterface;

@SuppressWarnings("unchecked")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public abstract class ServicosTestTemplateUtil<T> implements ServicosTestTemplateUtilIterface<T> {
	
	@SuppressWarnings("rawtypes")
	private Object testPadrao(Type type, HttpMethod metodo, String url, Object object) {
		Response response;
		if(object == null) {
			response = (Response) ClientHelp.metodo(url, metodo, type);
		}
		else {
			response = (Response) ClientHelp.metodo(url, metodo, type, object);
		}
		assertNotNull(response);
		Object objeto = response.getData();
		assertNotNull(objeto);
		return objeto;
	}
	
	public void testFindAll(int sizeList, Type type) {
		try {
			List<T> objeto = null;
			objeto = (List<T>) testPadrao(type, HttpMethod.GET, getURL_ENTIDADE(), null);
			assertEquals(sizeList, objeto.size());
			for (T pos : objeto) {
				testAssertNotNullObjectAndId(pos);
			}
		} catch (Exception e) {
			assertTrue(Boolean.FALSE);
		}
	}
	
	public T testNewObject(T objeto) {
		try {
			objeto = (T) testPadrao(getType(), HttpMethod.POST, getURL_ENTIDADE(), objeto);
			testAssertNotNullObjectAndId(objeto);
			return objeto;
		} catch (Exception e) {
			assertTrue(Boolean.FALSE);
			throw e;
		}
	}

	public T testFindById() {
		try {
			final String url = getURL_ENTIDADE() + "/" + getId();
			T objeto = (T) testPadrao(getType(), HttpMethod.GET, url, null);
			testAssertNotNullObjectAndId(objeto);
			return objeto;
		} catch (Exception e) {
			assertTrue(Boolean.FALSE);
			throw e;
		}
	}

	public T testUpdate(T objeto) {
		try {
			objeto = (T) testPadrao(getType(), HttpMethod.PUT, getURL_ENTIDADE(), objeto);
			testAssertNotNullObjectAndId(objeto);
			return objeto;
		} catch (Exception e) {
			assertTrue(Boolean.FALSE);
			throw e;
		}
	}
	
	@SuppressWarnings("rawtypes")
	public Page[] testPaginacao(Type type, String ...url) {
		try {
			Page[] pages = new Page[url.length];
			Response<Page<T>> response = null;
			for (int i = 0; i < url.length; i++) {
				response = (Response<Page<T>>) ClientHelp.metodo(url[i], HttpMethod.GET, type);
				assertNotNull(response);
				pages[i] = (Page) response.getData();
				assertNotNull(response.getData().getContent());
			}
			return pages;
		} catch (Exception e) {
			assertTrue(Boolean.FALSE);
			throw e;
		}
	}

	@Test
	public void test06DeleteById() {
		try {
			final String url = getURL_ENTIDADE() + "/" + getId();
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
	
	public T testFindByField(String field, String value) {
		try {
			String url = String.format("%s/field=%s/value=%s", getURL_ENTIDADE(), field, value);
			T objeto = (T) testPadrao(getType(), HttpMethod.GET, url, null);
			testAssertNotNullObjectAndId(objeto);
			return objeto;
		} catch (Exception e) {
			assertTrue(Boolean.FALSE);
			throw e;
		}
	}
}
