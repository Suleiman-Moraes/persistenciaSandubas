package br.com.senaigo.persistenciasandubas.util;

import java.util.List;

public interface CRUDPadraoService<T> {
	public static final String OBJETO_EXISTENTE = "Objeto ja existente.";
	
	List<T> findAll();
	 
    T findById(String id);
    
    T save(T objeto) throws Exception;
 
    Boolean deleteById(String id);
    
    Boolean existsByField(String fieldName, String value) throws Exception;
    
    T findByField(String field, String value);
}
