package br.com.senaigo.persistenciasandubas;

import java.util.Locale;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.FixedLocaleResolver;

import br.com.senaigo.persistenciasandubas.model.Email;
import br.com.senaigo.persistenciasandubas.model.Telefone;
import br.com.senaigo.persistenciasandubas.model.Usuario;
import br.com.senaigo.persistenciasandubas.model.enummeration.FuncaoUsuarioEnum;
import br.com.senaigo.persistenciasandubas.model.enummeration.StatusUsuarioEnum;
import br.com.senaigo.persistenciasandubas.model.enummeration.TipoTelefoneEnum;
import br.com.senaigo.persistenciasandubas.service.UsuarioService;

@SpringBootApplication(scanBasePackages = { "br.com.senaigo.persistenciasandubas" })
public class PersistenciaSandubasApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(PersistenciaSandubasApplication.class, args);
	}
	
	@Bean
	CommandLineRunner init(UsuarioService service, PasswordEncoder passwordEncoder) {
		return args -> {
			initUsers(service, passwordEncoder);
		};
	}
	
	private void initUsers(UsuarioService service, PasswordEncoder passwordEncoder) {
		Usuario admin = new Usuario();
		admin.setEmail(new Email(null, "suleimanmoraes@gmail.com", Boolean.TRUE));
		admin.setSenha("123456");
		admin.setNome("root");
		admin.setLogin("root");
		admin.setStatusUsuarioEnum(StatusUsuarioEnum.ATIVO);
		admin.setFuncaoUsuarioEnum(FuncaoUsuarioEnum.ROOT);
		admin.setTelefone(new Telefone(null, "(62)998264577", TipoTelefoneEnum.CELULAR));
		
		Usuario find = service.findByField("login", admin.getLogin());
		if(find == null) {
			try {
				service.save(admin);
				System.out.println(String.format("Criando Usuário Admin, com E-mail/Login == \"root\" e Senha == \"123456\"."));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(PersistenciaSandubasApplication.class);
	}

	@Bean
	public LocaleResolver localeResolver() {
		return new FixedLocaleResolver(new Locale("pt", "BR"));
	}
}
