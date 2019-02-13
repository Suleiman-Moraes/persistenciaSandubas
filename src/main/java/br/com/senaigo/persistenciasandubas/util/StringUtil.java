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
}
