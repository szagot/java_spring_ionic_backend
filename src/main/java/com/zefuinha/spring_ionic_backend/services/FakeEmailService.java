package com.zefuinha.spring_ionic_backend.services;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;

/**
 * Para enviar emails apenas no console
 */
public class FakeEmailService extends AbstractEmailService {

	// Logs no console
	private static final Logger LOG = LoggerFactory.getLogger(FakeEmailService.class);

	@Override
	public void sendEmail(SimpleMailMessage message) {

		// Envia o conteúdo do email para o console
		LOG.info("Simulando envio de email...");
		LOG.info(message.toString());
		LOG.info("Email enviado!");

	}

	@Override
	public void sendEmailHtml(MimeMessage message) {

		// Envia o conteúdo do email para o console
		LOG.info("Simulando envio de email HTML...");
		LOG.info(message.toString());
		LOG.info("Email enviado!");

	}

}
