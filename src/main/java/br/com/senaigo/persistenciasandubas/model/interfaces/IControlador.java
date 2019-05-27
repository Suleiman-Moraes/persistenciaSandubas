package br.com.senaigo.persistenciasandubas.model.interfaces;

import java.util.List;

import org.springframework.http.ResponseEntity;

import br.com.senaigo.persistenciasandubas.response.Response;
import br.com.senaigo.persistenciasandubas.util.CRUDPadraoService;

public interface IControlador<T> {
    ResponseEntity<Response<List<T>>> findAll();
 
    ResponseEntity<Response<T>> findById(String id);
 
    ResponseEntity<Response<T>> newObject(T objeto);
 
    ResponseEntity<Response<T>> update(T objeto);
 
    ResponseEntity<Response<Boolean>> deleteById(String id);
    
    ResponseEntity<Response<T>> findByField(String field, String value);
    
    ResponseEntity<Response<Boolean>> existsByField(String field, String value);
    
    CRUDPadraoService<T> getService();
}
