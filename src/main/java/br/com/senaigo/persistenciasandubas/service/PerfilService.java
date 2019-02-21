package br.com.senaigo.persistenciasandubas.service;

import org.springframework.data.domain.Page;

import br.com.senaigo.persistenciasandubas.model.Perfil;
import br.com.senaigo.persistenciasandubas.util.CRUDPadraoService;
import br.com.senaigo.persistenciasandubas.util.FacesUtil;

public interface PerfilService extends CRUDPadraoService<Perfil>{
	public static final String OBJETO_EXISTENTE = FacesUtil.propertiesLoader().getProperty("perfilExistente");
	
    Page<Perfil> paginarComParemetros(Integer page, Integer count, Long id, String nome, String descricao, Long funcionalidadeId);
}
