package br.com.senaigo.persistenciasandubas.service;

import org.springframework.data.domain.Page;

import br.com.senaigo.persistenciasandubas.model.Funcionalidade;
import br.com.senaigo.persistenciasandubas.util.CRUDPadraoService;
import br.com.senaigo.persistenciasandubas.util.FacesUtil;

public interface FuncionalidadeService extends CRUDPadraoService<Funcionalidade>{
	public static final String OBJETO_EXISTENTE = FacesUtil.propertiesLoader().getProperty("funcionalidadeExistente");
	
    Page<Funcionalidade> paginarComParemetros(Integer page, Integer count, Long id, String descricao);
}
