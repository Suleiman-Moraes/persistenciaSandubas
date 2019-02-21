package br.com.senaigo.persistenciasandubas.service;

import org.springframework.data.domain.Page;

import br.com.senaigo.persistenciasandubas.model.ClassificacaoMercadoria;
import br.com.senaigo.persistenciasandubas.util.CRUDPadraoService;
import br.com.senaigo.persistenciasandubas.util.FacesUtil;

public interface ClassificacaoMercadoriaService extends CRUDPadraoService<ClassificacaoMercadoria> {
	public static final String OBJETO_EXISTENTE = FacesUtil.propertiesLoader().getProperty("classificacaoMercadoriaExistente");
	
    ClassificacaoMercadoria findById(Long id);
 
    Boolean deleteById(Long id);
    
    Boolean deleteById(ClassificacaoMercadoria objeto);
    
    Page<ClassificacaoMercadoria> paginarComParemetros(Integer page, Integer count, Long id, String nome, String descricao);
}
