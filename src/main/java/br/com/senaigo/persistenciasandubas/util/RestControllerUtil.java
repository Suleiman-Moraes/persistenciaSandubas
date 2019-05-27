package br.com.senaigo.persistenciasandubas.util;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.ResponseEntity;

import br.com.senaigo.persistenciasandubas.response.Response;

public abstract class RestControllerUtil {
	
	private static final Log logger = LogFactory.getLog(RestControllerUtil.class);
	/**
	 * 
	 * @param service
	 * @return
	 */
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
 
    /**
     * 
     * @param service
     * @param id
     * @return
     */
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
    
    /**
     * 
     * @param service
     * @param fieldName
     * @param value
     * @return
     */
    public static <T> Response<T> findByField(CRUDPadraoService<T> service, String fieldName, String value) {
    	Response<T> response = new Response<>();
    	try {
    		T objeto = service.findByField(fieldName, value);
    		response.setData(objeto);
    		return response;
		} catch (Exception e) {
			return mostrarErroPadraoObject(response, e.getMessage());
		}
    }
 
    /**
     * 
     * @param service
     * @param objeto
     * @return
     */
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
 
    /**
     * 
     * @param service
     * @param id
     * @return
     */
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
    
    public static <T> Response<Boolean> existsByField(CRUDPadraoService<T> service, String fieldName, String value) {
    	Response<Boolean> response = new Response<>();
    	try {
    		Boolean objeto = service.existsByField(fieldName, value);
    		response.setData(objeto);
    		return response;
		} catch (Exception e) {
			response.setData(Boolean.FALSE);
			List<String> listErro = new ArrayList<>();
			listErro.add(e.getMessage());
			response.setErros(listErro);
			return response;
		}
    }
    
    /**
     * 
     * @param response
     * @param erros
     * @return
     */
    public static <T> Response<T> mostrarErroPadraoObject(Response<T> response, String... erros) {
    	logger.error("Error no retorno da requisição");
		response.setData(null);
		List<String> listErro = new ArrayList<>();
		for (String erro : erros) {
			listErro.add(erro);
			logger.error(erro);
		}
		response.setErros(listErro);
		return response;
	}

    /**
	 * 
	 * @param service
	 * @return
	 */
    public static <T> ResponseEntity<Response<List<T>>> findAllCompleto(CRUDPadraoService<T> service) {
    	Response<List<T>> response = new Response<>();
		try {
	        List<T> listObject = service.findAll();
	        response.setData(listObject);
	        logarInfo("findAll com sucesso");
	        return ResponseEntity.ok(response);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(mostrarErroPadraoObject(response, e.getMessage()));
		}
    }
    
    /**
     * 
     * @param service
     * @param id
     * @return
     */
    public static <T> ResponseEntity<Response<T>> findByIdCompleto(CRUDPadraoService<T> service, String id) {
    	Response<T> response = new Response<>();
    	try {
    		T objeto = service.findById(id);
    		response.setData(objeto);
    		logarInfo("findById com sucesso");
    		return ResponseEntity.ok(response);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(mostrarErroPadraoObject(response, e.getMessage()));
		}
    }

    /**
     * 
     * @param service
     * @param objeto
     * @return
     */
    public static <T> ResponseEntity<Response<T>> saveCompleto(CRUDPadraoService<T> service, T objeto) {
    	Response<T> response = new Response<>();
    	try {
    		T objetoNovo = service.save(objeto);
    		response.setData(objetoNovo);
    		logarInfo("save com sucesso");
    		return ResponseEntity.ok(response);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(mostrarErroPadraoObject(response, e.getMessage()));
		}
    }

    /**
     * 
     * @param service
     * @param id
     * @return
     */
    public static <T> ResponseEntity<Response<Boolean>> deleteByIdCompleto(CRUDPadraoService<T> service, String id) {
    	Response<Boolean> response = new Response<>();
    	try {
    		Boolean retorno = service.deleteById(id);
			response.setData(retorno);
			logarInfo("deleteById com sucesso");
    		return ResponseEntity.ok(response);
		} catch (Exception e) {
			response.setData(Boolean.FALSE);
			List<String> listErro = new LinkedList<>();
			listErro.add(e.getMessage());
			response.setErros(listErro);
			logger.error("Error no retorno da requisição");
			logger.error(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
    }

	/**
     * 
     * @param service
     * @param fieldName
     * @param value
     * @return
     */
    public static <T> ResponseEntity<Response<T>> findByFieldCompleto(CRUDPadraoService<T> service, String fieldName, String value) {
    	Response<T> response = new Response<>();
    	try {
    		T objeto = service.findByField(fieldName, value);
    		response.setData(objeto);
    		logarInfo("findByField com sucesso");
    		return ResponseEntity.ok(response);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(mostrarErroPadraoObject(response, e.getMessage()));
		}
    }

    /**
     * 
     * @param service
     * @param fieldName
     * @param value
     * @return
     */
    public static <T> ResponseEntity<Response<Boolean>> existsByFieldCompleto(CRUDPadraoService<T> service, String fieldName, String value) {
    	Response<Boolean> response = new Response<>();
    	try {
    		Boolean objeto = service.existsByField(fieldName, value);
    		response.setData(objeto);
    		logarInfo("existsByField com sucesso");
    		return ResponseEntity.ok(response);
		} catch (Exception e) {
			response.setData(Boolean.FALSE);
			List<String> listErro = new LinkedList<>();
			listErro.add(e.getMessage());
			response.setErros(listErro);
			logger.error("Error no retorno da requisição");
			logger.error(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
    }
    
    public static <T> ResponseEntity<Response<T>> mostrarErroPadraoObject(Class<?> classe, Response<T> response, String... erros) {
    	logger.error("Error no retorno da requisição");
		response.setData(null);
		List<String> listErro = new LinkedList<>();
		for (String erro : erros) {
			listErro.add(erro);
			logarError(classe, erro);
		}
		response.setErros(listErro);
		return ResponseEntity.badRequest().body(response);
	}
    
    public static void logarError(Class<?> classe) {
    	logger.error("Error no retorno do controlador: " + classe.getName());
    }
    
    public static void logarError(Class<?> classe, String erro) {
    	logger.error("Error no retorno do controlador: " + classe.getName());
    	logger.error("Error no retorno do controlador: " + erro);
    }
    
    public static void logarInfo(Class<?> classe, String msg) {
    	logger.info("Informação no controlador: " + classe.getName());
    	logger.info(msg);
    }
    
    public static void logarInfo(String msg) {
    	logger.info(msg);
    }
    
    public static void logarError(Class<?> classe, String erro, String metodo) {
    	logger.error("Error no retorno do controlador: " + classe.getName());
    	logger.error("Error no retorno do controlador: " + erro);
    	logger.error("Error no retorno do controlador/metodo: " + metodo);
    }
}
