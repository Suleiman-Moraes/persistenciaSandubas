package br.com.senaigo.persistenciasandubas.service.implementacao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.senaigo.persistenciasandubas.model.ClassificacaoMercadoria;
import br.com.senaigo.persistenciasandubas.repository.ClassificacaoMercadoriaDAO;
import br.com.senaigo.persistenciasandubas.service.ClassificacaoMercadoriaService;
import lombok.Getter;

@Getter
@Service
public class ClassificacaoMercadoriaServiceIMPL implements ClassificacaoMercadoriaService {
	
	@Autowired
	ClassificacaoMercadoriaDAO persistencia;

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
	public ClassificacaoMercadoria save(ClassificacaoMercadoria objeto) {
		return persistencia.save(objeto);
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
	public Page<ClassificacaoMercadoria> findByParameters(Integer page, Integer count, Long id, String nome, String descricao) {
		Pageable pages = PageRequest.of(page, count);
		Page<ClassificacaoMercadoria> pagina = persistencia.findByIdAndNomeIgnoreCaseContainingAndDescricaoIgnoreCaseContainingOrderByIdDesc(id, nome, descricao, pages);
		return pagina;
	}
}
