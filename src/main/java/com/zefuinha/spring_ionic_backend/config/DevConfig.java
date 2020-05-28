package com.zefuinha.spring_ionic_backend.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.zefuinha.spring_ionic_backend.services.DBService;
import com.zefuinha.spring_ionic_backend.services.EmailService;
import com.zefuinha.spring_ionic_backend.services.SmtpEmailService;

/**
 * Configurações específicas do profile de Desenvolvimento
 */

@Configuration
@Profile("dev")
public class DevConfig {

	@Autowired
	private DBService dbService;

	// Pega a variável do application.properties do perfil em uso
	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String strategy;

	/**
	 * Responsável por instanciar o banco de dados para teste
	 * 
	 * @throws ParseException
	 */
	@Bean
	public boolean instantiateDatabase() throws ParseException {

		// Se a estratégia do BD não envolver criar, o sistema tb não instancia as tabelas novamente
		if (strategy == null || !strategy.equals("create")) {
			return false;
		}

		// Instancia as tabelas do BD
		dbService.instantiateTestDataBase();

		return true;
	}

	/**
	 * Seta qual deve ser o serviço de email utilizado no perfil de desenvolvimento
	 * 
	 * @return
	 */
	@Bean
	public EmailService emailService() {
		return new SmtpEmailService();
	}
	
}
