package br.com.senaigo.persistenciasandubas.service.implementacao;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.senaigo.persistenciasandubas.model.Perfil;
import br.com.senaigo.persistenciasandubas.model.Usuario;
import br.com.senaigo.persistenciasandubas.model.enummeration.FuncaoUsuarioEnum;
import br.com.senaigo.persistenciasandubas.model.enummeration.StatusUsuarioEnum;
import br.com.senaigo.persistenciasandubas.repository.UsuarioDAO;
import br.com.senaigo.persistenciasandubas.repository.hql.GenercicDAO;
import br.com.senaigo.persistenciasandubas.service.PerfilService;
import br.com.senaigo.persistenciasandubas.service.UsuarioService;
import br.com.senaigo.persistenciasandubas.util.ConstantesUtil;
import br.com.senaigo.persistenciasandubas.util.FacesUtil;
import br.com.senaigo.persistenciasandubas.util.StringUtil;
import lombok.Getter;

@Getter
@Service
public class UsuarioServiceIMPL implements UsuarioService{
	
	@Autowired
	private UsuarioDAO persistencia;
	
	@Autowired
	private PerfilService perfilService;
	
	@Autowired
	private GenercicDAO genericDAO;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public List<Usuario> findAll() {
		return persistencia.findAll();
	}

	@Override
	public Usuario findById(String id) {
		return persistencia.findById(new Long(id)).get();
	}

	@Override
	public Usuario save(Usuario objeto) throws Exception {
		try {
			validarUsuario(objeto);
			if(objeto.getStatusUsuarioEnum() == null || objeto.getStatusUsuarioEnum().equals(StatusUsuarioEnum.INATIVO)) {
				objeto.setStatusUsuarioEnum(StatusUsuarioEnum.INATIVO);
				objeto.setDataDesativacao(new Date());
			}
			else {
				objeto.setDataAtivacao(new Date());
			}
			if(StringUtil.isNotNullOrEmpity(objeto.getSenha()) && objeto.getSenha().length() <= 15) {
				objeto.setSenha(passwordEncoder.encode(objeto.getSenha()));
			}
			else if(StringUtil.isNullOrEmpity(objeto.getSenha())) {
				objeto.setSenha(passwordEncoder.encode(FacesUtil.propertiesLoader().getProperty("usuarioSenhaPadrao")));
			}
			List<Perfil> perfis = new LinkedList<>();
			perfis.add(genericDAO.findByIdEager(Perfil.class, objeto.getFuncaoUsuarioEnum().getId()));
			objeto.setPerfis(perfis);
			
			if(objeto.getId() == null || objeto.getId() <= 0) {
				if(existsByField("login", objeto.getLogin())) {
					throw new Exception(OBJETO_EXISTENTE);
				}
			}
			else {
				Usuario aux = findByField("login", objeto.getLogin());
				if(aux != null && aux.getId() != objeto.getId()) {
					throw new Exception(OBJETO_EXISTENTE);
				}
			}
			return genericDAO.update(objeto);
		} catch (Exception e) {
			throw e;
		}
	}
	
	@Override
	public Boolean deleteById(String id) {
		try {
			persistencia.deleteById(new Long(id));
			return Boolean.TRUE;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public Boolean existsByField(String fieldName, String value) throws Exception {
		try {
			Boolean objeto = genericDAO.existsByField(Usuario.class, fieldName, value);
			return objeto;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public Usuario findByField(String field, String value) {
		try {
			Usuario objeto = genericDAO.findByField(Usuario.class, field, value);
			return objeto;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Page<Usuario> paginarComParemetros(Integer page, Integer count, Long id, String nome, String login,
			String statusUsuarioEnum, String funcaoUsuarioEnum) {
		nome = StringUtil.tratarStringUninformed(nome);
		login = StringUtil.tratarStringUninformed(login);
		Pageable pages = PageRequest.of(page, count);
		Page<Usuario> pagina = null;
		if(id != null && id > 0) {
			pagina = persistencia.findById(id, pages);
		}
		else if(!statusUsuarioEnum.equals(ConstantesUtil.UNINFORMED)) {
			pagina = persistencia.findByStatusUsuarioEnum(StatusUsuarioEnum.get(statusUsuarioEnum), pages);
		}
		else if(!funcaoUsuarioEnum.equals(ConstantesUtil.UNINFORMED)) {
			pagina = persistencia.findByFuncaoUsuarioEnum(FuncaoUsuarioEnum.get(funcaoUsuarioEnum), pages);
		}
		else {
			pagina = persistencia.
					findByNomeIgnoreCaseContainingAndLoginIgnoreCaseContainingOrderByIdDesc
					(nome, login, pages);
		}
		return pagina;
	}
	
	@Override
	public Usuario inativarUsuarioById(String id) throws Exception {
		try {
			Usuario objeto = persistencia.findById(Long.valueOf(id)).get();
			objeto.setStatusUsuarioEnum(StatusUsuarioEnum.INATIVO);
			return save(objeto);
		} catch (Exception e) {
			throw e;
		}
	}
	
	private void validarUsuario(Usuario objeto) throws Exception{
		if(StringUtil.isNullOrEmpity(objeto.getNome())) {
			throw new Exception(FacesUtil.propertiesLoader().getProperty("usuarioNomeNaoInserido"));
		}
		if(StringUtil.isNullOrEmpity(objeto.getLogin())) {
			throw new Exception(FacesUtil.propertiesLoader().getProperty("usuarioLoginNaoInserido"));
		}
		if(objeto.getFuncaoUsuarioEnum() == null) {
			throw new Exception(FacesUtil.propertiesLoader().getProperty("usuarioFuncaoUsuarioEnumNaoInserido"));
		}
		if(StringUtil.isNullOrEmpity(objeto.getEmail().getEmail())) {
			throw new Exception("usuarioEmailNaoInserido");
		}
	}
}
