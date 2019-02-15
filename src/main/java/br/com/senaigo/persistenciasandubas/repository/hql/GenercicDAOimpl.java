package br.com.senaigo.persistenciasandubas.repository.hql;

import java.lang.reflect.Field;

import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Table;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@SuppressWarnings("unchecked")
@Transactional
@Repository
public class GenercicDAOimpl implements GenercicDAO{
	
	@PersistenceContext	
	private EntityManager entityManager;

	@Override
	public <T> T findByField(Class<?> clazz, String field, Object value) {
		try {
			final String entityName = clazz.getSimpleName();
			final String entityNick = entityName.toLowerCase();
			final String hql = String.format("SELECT %s FROM %s %s WHERE %s.%s = '%s'", 
					entityNick, entityName, entityNick, entityNick, field, value);
			T objeto = (T) entityManager.createQuery(hql, clazz).setMaxResults(1).getSingleResult();
			return objeto;
		} catch (Exception e) {
			throw e;
		}
	}
	
	@Override
	public Boolean existsByField(Class<?> clazz, String field, Object value) throws Exception {
		try {
			Long objeto = countByField(clazz, field, value);
			if(objeto != null && objeto > 0) {
				return Boolean.TRUE;
			}
			return Boolean.FALSE;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public Long countByField(Class<?> clazz, String field, Object value) throws Exception {
		try {
			Table table = clazz.getAnnotation(Table.class);
			final String tableName = table.name();
			String fieldName = "";
			Field fieldd = clazz.getField(field);
			if(fieldd.getAnnotation(Column.class) != null) {
				fieldName = fieldd.getAnnotation(Column.class).name();
			}
			else {
				fieldName = field;
			}
			final String sql = String.format("SELECT COUNT(*) FROM %s WHERE %s = '%s'", 
					tableName, fieldName, value);
			Long objeto = new Long(entityManager.createNativeQuery(sql).getSingleResult() + "");
			return objeto;
		} catch (Exception e) {
			throw e;
		}
	}
}
