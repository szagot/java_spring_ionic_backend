package com.zefuinha.spring_ionic_backend.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.zefuinha.spring_ionic_backend.services.DBService;
import com.zefuinha.spring_ionic_backend.services.EmailService;
import com.zefuinha.spring_ionic_backend.services.FakeEmailService;

/**
 * Configurações específicas do profile de teste
 */

@Configuration
@Profile("test")
public class TestConfig {

	@Autowired
	private DBService dbService;

	/**
	 * Responsável por instanciar o banco de dados para teste
	 * 
	 * @throws ParseException
	 */
	@Bean
	public boolean instantiateDatabase() throws ParseException {

		// Instancia as tabelas do BD
		dbService.instantiateTestDataBase();

		return true;
	}

	/**
	 * Seta qual deve ser o serviço de email utilizado no perfil de teste
	 * 
	 * @return
	 */
	@Bean
	public EmailService emailService() {
		return new FakeEmailService();
	}

}
