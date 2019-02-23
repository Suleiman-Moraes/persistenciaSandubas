package br.com.senaigo.persistenciasandubas.repository.hql;

import java.lang.reflect.Field;

import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PersistenceContext;
import javax.persistence.Table;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.senaigo.persistenciasandubas.util.StringUtil;

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
			return null;
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
			return Boolean.FALSE;
		}
	}

	@Override
	public Long countByField(Class<?> clazz, String field, Object value) throws Exception {
		try {
			Table table = clazz.getAnnotation(Table.class);
			final String tableName = table.name();
			String fieldName = "";
			Field fieldd = clazz.getDeclaredField(field);
			Column column = fieldd.getAnnotation(Column.class);
			if(column != null && StringUtil.isNotNullOrEmpity(column.name())) {
				fieldName = column.name();
			}
			else {
				fieldName = field;
			}
			final String sql = String.format("SELECT COUNT(*) FROM %s WHERE %s = '%s'", 
					tableName, fieldName, value);
			Long objeto = new Long(entityManager.createNativeQuery(sql).getSingleResult() + "");
			return objeto;
		} catch (Exception e) {
			return new Long(0);
		}
	}
	
	@Override
	public <T> T findByIdEager(Class<T> type, String pk) {
		Table table = type.getAnnotation(javax.persistence.Table.class);
		if (table != null) {

			String hql = String.format("SELECT %s FROM %s %s", type.getSimpleName().toLowerCase(), type.getSimpleName(),
					type.getSimpleName().toLowerCase());
			//JOIN FETCH apelidoTabela.atributo apelidoAtributo
			Field[] atribustos = type.getDeclaredFields();
			StringBuilder joins = new StringBuilder("");
			StringBuilder where = new StringBuilder("");
			for(Field atributo : atribustos) {
				if(atributo.getAnnotation(ManyToOne.class) != null 
						|| atributo.getAnnotation(OneToOne.class) != null 
						|| atributo.getAnnotation(ManyToMany.class) != null 
						|| atributo.getAnnotation(OneToMany.class) != null) {
					joins.append(" JOIN FETCH ").append(type.getSimpleName().toLowerCase());
					joins.append(".").append(atributo.getName()).append(" ");
					joins.append(atributo.getName().toLowerCase());
				}else if(atributo.getAnnotation(Id.class) != null) {
					where.append(" WHERE ").append(type.getSimpleName().toLowerCase());
					where.append(".").append(atributo.getName()).append(" = '").append(pk).append("'");
				}
			}
			hql += joins.toString();
			hql += where.toString();
			return entityManager.createQuery(hql, type).setMaxResults(1).getSingleResult();

		}
		return null;
	}

	@Override
	public <T> T update(T entity) {
		return entityManager.merge(entity);
	}
}
