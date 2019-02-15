package br.com.senaigo.persistenciasandubas.util;

import java.util.List;

public interface CRUDPadraoService<T> {
	List<T> findAll();
	 
    T findById(String id);
    
    T save(T objeto);
 
    Boolean deleteById(String id);
    
    Boolean existsByField(String fieldName, String value) throws Exception;
    
    T findByField(String field, String value);
}
