package br.com.senaigo.persistenciasandubas.service.implementacao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.senaigo.persistenciasandubas.model.Funcionalidade;
import br.com.senaigo.persistenciasandubas.repository.FuncionalidadeDAO;
import br.com.senaigo.persistenciasandubas.repository.hql.GenercicDAO;
import br.com.senaigo.persistenciasandubas.service.FuncionalidadeService;
import br.com.senaigo.persistenciasandubas.util.StringUtil;
import lombok.Getter;

@Getter
@Service
public class FuncionalidadeServiceIMPL implements FuncionalidadeService{
	
	@Autowired
	private FuncionalidadeDAO persistencia;
	
	@Autowired
	private GenercicDAO genericDAO;

	@Override
	public List<Funcionalidade> findAll() {
		return persistencia.findAll();
	}

	@Override
	public Funcionalidade findById(String id) {
		return persistencia.findById(new Long(id)).get();
	}

	@Override
	public Funcionalidade save(Funcionalidade objeto) throws Exception {
		try {
			if(objeto.getId() == null || objeto.getId() <= 0) {
				if(existsByField("descricao", objeto.getDescricao())) {
					throw new Exception(OBJETO_EXISTENTE);
				}
			}
			else {
				Funcionalidade aux = findByField("descricao", objeto.getDescricao());
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
			Boolean objeto = genericDAO.existsByField(Funcionalidade.class, fieldName, value);
			return objeto;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public Funcionalidade findByField(String field, String value) {
		try {
			Funcionalidade objeto = genericDAO.findByField(Funcionalidade.class, field, value);
			return objeto;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public Page<Funcionalidade> paginarComParemetros(Integer page, Integer count, Long id, String descricao) {
    	descricao = StringUtil.tratarStringUninformed(descricao);
		Pageable pages = PageRequest.of(page, count);
		Page<Funcionalidade> pagina = null;
		if(id != null && id > 0) {
			pagina = persistencia.findById(id, pages);
		}
		else {
			pagina = persistencia.findByDescricaoIgnoreCaseContainingOrderByIdDesc(descricao, pages);
		}
		return pagina;
	}
}
