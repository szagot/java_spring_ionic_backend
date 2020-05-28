package com.zefuinha.spring_ionic_backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.zefuinha.spring_ionic_backend.services.EmailService;
import com.zefuinha.spring_ionic_backend.services.SmtpEmailService;

/**
 * Configurações específicas do profile de Produção
 */

@Configuration
@Profile("prod")
public class ProdConfig {
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
