package br.com.senaigo.persistenciasandubas.service;

import java.util.List;

import br.com.senaigo.persistenciasandubas.model.ClassificacaoMercadoria;
import br.com.senaigo.persistenciasandubas.response.Response;

public interface ClassificacaoMercadoriaService {
    List<ClassificacaoMercadoria> findAll();
 
    ClassificacaoMercadoria findById(String id);
    
    ClassificacaoMercadoria findById(Long id);
 
    ClassificacaoMercadoria save(ClassificacaoMercadoria objeto);
 
    Boolean deleteById(String id);
    
    Boolean deleteById(Long id);
    
    Boolean deleteById(ClassificacaoMercadoria objeto);
    
   <T> Response<T> mostrarErroPadraoObject(String... erros);
}
