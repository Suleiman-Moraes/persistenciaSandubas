package br.com.senaigo.persistenciasandubas.model.interfaces;

import java.io.Serializable;

public interface ICRUDService<T extends Serializable>{
	void salvar(T objeto) throws Exception;
	void excluir(T objeto) throws Exception;
	public Boolean registroExiste(T objeto);
}
