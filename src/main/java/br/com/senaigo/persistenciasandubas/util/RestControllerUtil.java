package br.com.senaigo.persistenciasandubas.util;

import java.util.ArrayList;
import java.util.List;

import br.com.senaigo.persistenciasandubas.response.Response;

public abstract class RestControllerUtil {
    public static <T> Response<List<T>> findAll(CRUDPadraoService<T> service) {
    	Response<List<T>> response = new Response<>();
		try {
	        List<T> listObject = service.findAll();
	        response.setData(listObject);
	        return response;
		} catch (Exception e) {
			return mostrarErroPadraoObject(response, e.getMessage());
		}
    }
 
    public static <T> Response<T> findById(CRUDPadraoService<T> service, String id) {
    	Response<T> response = new Response<>();
    	try {
    		T objeto = service.findById(id);
    		response.setData(objeto);
    		return response;
		} catch (Exception e) {
			return mostrarErroPadraoObject(response, e.getMessage());
		}
    }
 
    public static <T> Response<T> save(CRUDPadraoService<T> service, T objeto) {
    	Response<T> response = new Response<>();
    	try {
    		T objetoNovo = service.save(objeto);
    		response.setData(objetoNovo);
    		return response; 
		} catch (Exception e) {
			return mostrarErroPadraoObject(response, e.getMessage());
		}
    }
 
    public static <T> Response<Boolean> deleteById(CRUDPadraoService<T> service, String id) {
    	Response<Boolean> response = new Response<>();
    	try {
    		Boolean retorno = service.deleteById(id);
			response.setData(retorno);
			return response;
		} catch (Exception e) {
			response.setData(Boolean.FALSE);
			List<String> listErro = new ArrayList<>();
			listErro.add(e.getMessage());
			response.setErros(listErro);
			return response;
		}
    }
    
    public static <T> Response<T> mostrarErroPadraoObject(Response<T> response, String... erros) {
		response.setData(null);
		List<String> listErro = new ArrayList<>();
		for (String erro : erros) {
			listErro.add(erro);
		}
		response.setErros(listErro);
		return response;
	}
}
