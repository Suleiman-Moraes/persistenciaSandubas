package br.com.senaigo.persistenciasandubas.service.implementacao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.senaigo.persistenciasandubas.model.ClassificacaoMercadoria;
import br.com.senaigo.persistenciasandubas.repository.ClassificacaoMercadoriaDAO;
import br.com.senaigo.persistenciasandubas.repository.hql.GenercicDAO;
import br.com.senaigo.persistenciasandubas.service.ClassificacaoMercadoriaService;
import br.com.senaigo.persistenciasandubas.util.StringUtil;
import lombok.Getter;

@Getter
@Service
public class ClassificacaoMercadoriaServiceIMPL implements ClassificacaoMercadoriaService {
	
	@Autowired
	private ClassificacaoMercadoriaDAO persistencia;
	
	@Autowired
	private GenercicDAO genericDAO;

	@Override
	public List<ClassificacaoMercadoria> findAll() {
		return persistencia.findAll();
	}

	@Override
	public ClassificacaoMercadoria findById(String id) {
		return this.findById(new Long(id));
	}

	@Override
	public ClassificacaoMercadoria findById(Long id) {
		return persistencia.findById(id).get();
	}

	@Override
	public ClassificacaoMercadoria save(ClassificacaoMercadoria objeto) throws Exception{
		try {
			if(objeto.getId() == null || objeto.getId() <= 0) {
				if(existsByField("nome", objeto.getNome())) {
					throw new Exception(OBJETO_EXISTENTE);
				}
			}
			else {
				ClassificacaoMercadoria aux = findByField("nome", objeto.getNome());
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
		return this.deleteById(new Long(id));
	}

	@Override
	public Boolean deleteById(Long id) {
		try {
			persistencia.deleteById(id);
			return Boolean.TRUE;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public Boolean deleteById(ClassificacaoMercadoria objeto) {
		return this.deleteById(objeto.getId());
	}

	@Override
	public Page<ClassificacaoMercadoria> paginarComParemetros(Integer page, Integer count, Long id, String nome, String descricao) {
		nome = StringUtil.tratarStringUninformed(nome);
    	descricao = StringUtil.tratarStringUninformed(descricao);
		Pageable pages = PageRequest.of(page, count);
		Page<ClassificacaoMercadoria> pagina = null;
		if(id != null && id > 0) {
			pagina = persistencia.findById(id, pages);
		}
		else {
			pagina = persistencia.findByNomeIgnoreCaseContainingAndDescricaoIgnoreCaseContainingOrderByIdDesc(nome, descricao, pages);
		}
		return pagina;
	}

	@Override
	public ClassificacaoMercadoria findByField(String field, String value) {
		try {
			ClassificacaoMercadoria objeto = genericDAO.findByField(ClassificacaoMercadoria.class, field, value);
			return objeto;
		} catch (Exception e) {
			throw e;
		}
	}
	
	@Override
	public Boolean existsByField(String fieldName, String value) throws Exception {
		try {
			Boolean objeto = genericDAO.existsByField(ClassificacaoMercadoria.class, fieldName, value);
			return objeto;
		} catch (Exception e) {
			throw e;
		}
	}
}
