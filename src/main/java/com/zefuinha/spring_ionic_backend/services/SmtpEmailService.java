package com.zefuinha.spring_ionic_backend.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

public class SmtpEmailService extends AbstractEmailService {

	// Essa instância recebe os dados configurados no profile automaticamente
	@Autowired
	private MailSender mailsender;

	// Logs no console
	private static final Logger LOG = LoggerFactory.getLogger(SmtpEmailService.class);

	@Override
	public void sendEmail(SimpleMailMessage message) {
		// Envia o conteúdo do email para o console
		LOG.info("Enviando email...");
		LOG.info(message.toString());
		
		// Envia o email de fato
		mailsender.send(message);
		
		// Log de finalização
		LOG.info("Email enviado!");

	}

}
