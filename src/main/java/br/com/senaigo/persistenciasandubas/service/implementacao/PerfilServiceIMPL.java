package br.com.senaigo.persistenciasandubas.service.implementacao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.senaigo.persistenciasandubas.model.Perfil;
import br.com.senaigo.persistenciasandubas.repository.PerfilDAO;
import br.com.senaigo.persistenciasandubas.repository.hql.GenercicDAO;
import br.com.senaigo.persistenciasandubas.service.PerfilService;
import br.com.senaigo.persistenciasandubas.util.StringUtil;
import lombok.Getter;

@Getter
@Service
public class PerfilServiceIMPL implements PerfilService{
	
	@Autowired
	private PerfilDAO persistencia;
	
	@Autowired
	private GenercicDAO genericDAO;

	@Override
	public List<Perfil> findAll() {
		return persistencia.findAll();
	}

	@Override
	public Perfil findById(String id) {
		return persistencia.findById(new Long(id)).get();
	}

	@Override
	public Perfil save(Perfil objeto) throws Exception {
		try {
			if(objeto.getId() == null || objeto.getId() <= 0) {
				if(existsByField("nome", objeto.getNome())) {
					throw new Exception(OBJETO_EXISTENTE);
				}
			}
			else {
				Perfil aux = findByField("nome", objeto.getNome());
				if(aux != null && aux.getId() != objeto.getId()) {
					throw new Exception(OBJETO_EXISTENTE);
				}
			}
			return persistencia.save(objeto);
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
			Boolean objeto = genericDAO.existsByField(Perfil.class, fieldName, value);
			return objeto;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public Perfil findByField(String field, String value) {
		try {
			Perfil objeto = genericDAO.findByField(Perfil.class, field, value);
			return objeto;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public Page<Perfil> paginarComParemetros(Integer page, Integer count, Long id, String nome, String descricao, Long funcionalidadeId) {
		nome = StringUtil.tratarStringUninformed(nome);
		descricao = StringUtil.tratarStringUninformed(descricao);
		Pageable pages = PageRequest.of(page, count);
		Page<Perfil> pagina = null;
		if(id != null && id > 0) {
			pagina = persistencia.findById(id, pages);
		}
		else if(funcionalidadeId != null && funcionalidadeId > 0){
			pagina = persistencia.
					findByNomeIgnoreCaseContainingAndDescricaoIgnoreCaseContainingAndFuncionalidadesIdOrderByIdDesc
					(nome, descricao, funcionalidadeId, pages);
		}
		else {
			pagina = persistencia.
					findByNomeIgnoreCaseContainingAndDescricaoIgnoreCaseContainingOrderByIdDesc
					(nome, descricao, pages);
		}
		return pagina;
	}
}
