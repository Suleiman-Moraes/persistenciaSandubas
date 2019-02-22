package br.com.senaigo.persistenciasandubas.model.enummeration;

import lombok.Getter;

@Getter
public enum TipoTelefoneEnum {
	FIXO("Fixo"),
	CELULAR("Celular");
	
	private String descricao;
	
	private TipoTelefoneEnum(String descricao) {
		this.descricao = descricao;
	}
}
