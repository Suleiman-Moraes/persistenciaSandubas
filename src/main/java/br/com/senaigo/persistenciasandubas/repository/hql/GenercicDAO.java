package br.com.senaigo.persistenciasandubas.repository.hql;

public interface GenercicDAO {
	<T> T findByField(Class<?> clazz, String field, Object value);
}
