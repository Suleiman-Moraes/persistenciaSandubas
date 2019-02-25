package br.com.senaigo.persistenciasandubas.service;

import org.springframework.data.domain.Page;

import br.com.senaigo.persistenciasandubas.model.Usuario;
import br.com.senaigo.persistenciasandubas.util.CRUDPadraoService;
import br.com.senaigo.persistenciasandubas.util.FacesUtil;

public interface UsuarioService extends CRUDPadraoService<Usuario> {
	public static final String OBJETO_EXISTENTE = FacesUtil.propertiesLoader().getProperty("usuarioExistente");

	Page<Usuario> paginarComParemetros(Integer page, Integer count, Long id, String nome, String login,
			String statusUsuarioEnum, String funcaoUsuarioEnum);
	
	Usuario inativarUsuarioById(String id) throws Exception;
}
