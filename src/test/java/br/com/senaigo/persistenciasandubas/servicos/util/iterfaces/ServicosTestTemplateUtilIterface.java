package br.com.senaigo.persistenciasandubas.servicos.util.iterfaces;

import java.lang.reflect.Type;

public interface ServicosTestTemplateUtilIterface<T> {
	
	void test01FindAll();
	
	void test02NewObject();

	void test03FindById();

	void test04Update();
	
	void test05Paginacao();

	void testAssertNotNullObjectAndId(T objeto);
	
	String getURL_ENTIDADE();
	
	String getId();
	
	Type getType();
}
