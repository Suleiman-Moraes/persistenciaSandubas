package br.com.senaigo.persistenciasandubas.repository.hql;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
}
