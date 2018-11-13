package br.com.senaigo.persistenciasandubas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = { "br.com.senaigo.persistenciasandubas" })
public class PersistenciaSandubasApplication {

	public static void main(String[] args) {
		SpringApplication.run(PersistenciaSandubasApplication.class, args);
	}
}
