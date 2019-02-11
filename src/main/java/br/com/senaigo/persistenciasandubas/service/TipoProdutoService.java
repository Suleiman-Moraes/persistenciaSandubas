package br.com.senaigo.persistenciasandubas.service;

import br.com.senaigo.persistenciasandubas.model.TipoProduto;
import br.com.senaigo.persistenciasandubas.util.CRUDPadraoService;

public interface TipoProdutoService extends CRUDPadraoService<TipoProduto> {
	TipoProduto findById(Long id);
	 
    Boolean deleteById(Long id);
    
    Boolean deleteById(TipoProduto objeto);
}
