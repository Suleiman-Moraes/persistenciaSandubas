package br.com.senaigo.persistenciasandubas.servicos.util;

import java.lang.reflect.Type;
import java.util.Arrays;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;

import br.com.senaigo.persistenciasandubas.model.Token;
import br.com.senaigo.persistenciasandubas.model.UserMobile;

public class ClientHelp {
//	public static final String URL = "https://persistenciasandubas.herokuapp.com";
	public static final String URL = "http://localhost:8080";
	
	private static String TOKEN = null;
	
	public static <T> Object metodo(String url, HttpMethod httpMethod, Type type) {
		return metodo(url, httpMethod, type, null);
	}
	
	public static <T> Object metodo(String url, HttpMethod httpMethod, Type type, T object) {
		atualizarToken();
		HttpHeaders headers = new HttpHeaders();
		
		headers.setAccept(Arrays.asList(new MediaType[] {MediaType.APPLICATION_JSON}));
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", TOKEN);
		
		HttpEntity<T> entity = new HttpEntity<>(object, headers);
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.exchange(url, httpMethod, entity, String.class);
		HttpStatus httpStatus = response.getStatusCode();
		System.out.println("Status" + httpStatus);
		String result = response.getBody();
		
		try {
			Gson gson = new Gson();
			Object objectReturn = gson.fromJson(result, type);
			System.out.println(objectReturn);
			return objectReturn;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private static void atualizarToken() {
		if(TOKEN == null) {
			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Arrays.asList(new MediaType[] {MediaType.APPLICATION_JSON}));
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<UserMobile> entity = new HttpEntity<>(new UserMobile(), headers);
			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<Token> response = restTemplate.exchange(URL + "/auth", HttpMethod.POST, entity, Token.class);
			HttpStatus httpStatus = response.getStatusCode();
			System.out.println("Status" + httpStatus);
			Token result = response.getBody();
			TOKEN = result.getToken();
		}
	}
}
