package br.com.senaigo.persistenciasandubas.service.implementacao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.senaigo.persistenciasandubas.model.TipoProduto;
import br.com.senaigo.persistenciasandubas.repository.TipoProdutoDAO;
import br.com.senaigo.persistenciasandubas.service.TipoProdutoService;
import lombok.Getter;

@Getter
@Service
public class TipoProdutoServiceIMPL implements TipoProdutoService {
	
	@Autowired
	TipoProdutoDAO persistencia;

	@Override
	public List<TipoProduto> findAll() {
		return persistencia.findAll();
	}

	@Override
	public TipoProduto findById(String id) {
		return this.findById(new Long(id));
	}

	@Override
	public TipoProduto findById(Long id) {
		return persistencia.findById(id).get();
	}

	@Override
	public TipoProduto save(TipoProduto objeto) {
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
	public Boolean deleteById(TipoProduto objeto) {
		return this.deleteById(objeto.getId());
	}

	@Override
	public TipoProduto findByField(String field, String value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean existsByField(String fieldName, String value) {
		// TODO Auto-generated method stub
		return null;
	}
}
