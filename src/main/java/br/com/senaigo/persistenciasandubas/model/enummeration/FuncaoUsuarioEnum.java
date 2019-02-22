package br.com.senaigo.persistenciasandubas.model.enummeration;

import lombok.Getter;

@Getter
public enum FuncaoUsuarioEnum {
	
	ROOT("1","root"),
	ADMINISTRADOR("2","Administrador"),
    OPERADOR_CAIXA("3","Operador de Caixa"),
    FUNCIONARIO("4","Funcionário"),
	USUARIO_EXTERNO("5","Cliente");
    
    private String id;
    private String descricao;

    private FuncaoUsuarioEnum(String id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }
    
    public static FuncaoUsuarioEnum get(String funcaoUsuarioEnum) {
    	switch (funcaoUsuarioEnum.toUpperCase()) {
    	case "ROOT":
    		return FuncaoUsuarioEnum.ROOT;
    	case "ADMINISTRADOR":
    		return FuncaoUsuarioEnum.ADMINISTRADOR;
    	case "OPERADOR_CAIXA":
    		return FuncaoUsuarioEnum.OPERADOR_CAIXA;
    	case "FUNCIONARIO":
    		return FuncaoUsuarioEnum.FUNCIONARIO;
		case "USUARIO_EXTERNO":
			return FuncaoUsuarioEnum.USUARIO_EXTERNO;
		default:
			return FuncaoUsuarioEnum.USUARIO_EXTERNO;
		}
    }
}
