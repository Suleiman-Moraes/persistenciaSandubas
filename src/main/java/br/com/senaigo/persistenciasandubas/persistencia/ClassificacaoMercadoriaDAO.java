package br.com.senaigo.persistenciasandubas.persistencia;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.senaigo.persistenciasandubas.model.ClassificacaoMercadoria;
import br.com.senaigo.persistenciasandubas.repository.ClassificacaoMercadorias;
import lombok.Setter;

@Repository("ClassificacaoMercadoriaDAO")
public abstract class ClassificacaoMercadoriaDAO implements ClassificacaoMercadorias{
		@Setter
		@PersistenceContext
	    protected EntityManager entityManager;

	    @Transactional
	    public void update(ClassificacaoMercadoria objeto) {
	        entityManager.merge(objeto);
	    }
}
