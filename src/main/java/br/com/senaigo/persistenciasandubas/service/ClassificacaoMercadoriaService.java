package br.com.senaigo.persistenciasandubas.service;

import br.com.senaigo.persistenciasandubas.model.ClassificacaoMercadoria;
import br.com.senaigo.persistenciasandubas.util.CRUDPadraoService;

public interface ClassificacaoMercadoriaService extends CRUDPadraoService<ClassificacaoMercadoria> {
    ClassificacaoMercadoria findById(Long id);
 
    Boolean deleteById(Long id);
    
    Boolean deleteById(ClassificacaoMercadoria objeto);
}
