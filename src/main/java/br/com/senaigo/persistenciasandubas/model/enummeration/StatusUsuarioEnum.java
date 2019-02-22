package br.com.senaigo.persistenciasandubas.model.enummeration;

import lombok.Getter;

@Getter
public enum StatusUsuarioEnum {
	
    INATIVO(0, "Inativo"), 
    ATIVO(1, "Ativo");
    
    private Integer id;
    private String descricao;

    private StatusUsuarioEnum(Integer id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }
    
    public static StatusUsuarioEnum get(String statusUsuarioEnum) {
    	switch (statusUsuarioEnum.toUpperCase()) {
    	case "ATIVO":
    		return StatusUsuarioEnum.ATIVO;
		case "INATIVO":
			return StatusUsuarioEnum.INATIVO;
		default:
			return StatusUsuarioEnum.INATIVO;
		}
    }
}
