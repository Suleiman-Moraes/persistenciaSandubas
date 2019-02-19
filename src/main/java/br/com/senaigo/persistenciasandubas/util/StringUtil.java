package br.com.senaigo.persistenciasandubas.util;

public class StringUtil {
	/**
     * 
     * @param texto
     * @return
     */
    public static String tratarStringUninformed(String texto) {
    	texto = texto.equals(ConstantesUtil.UNINFORMED) ? "" : texto;
    	return texto;
    }
    
    public static Boolean isEmpity(String texto) {
    	return texto.trim().equals("");
    }
    
    public static Boolean isNull(String texto) {
    	return texto == null;
    }
    
    public static Boolean isNotEmpity(String texto) {
    	return !isEmpity(texto);
    }
    
    public static Boolean isNotNull(String texto) {
    	return !isNull(texto);
    }
    
    public static Boolean isNullOrEmpity(String texto) {
    	return isNull(texto) || isEmpity(texto);
    }
    
    public static Boolean isNotNullOrEmpity(String texto) {
    	return isNotNull(texto) && isNotEmpity(texto);
    }
}
