package com.zefuinha.spring_ionic_backend.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import com.zefuinha.spring_ionic_backend.domain.Pedido;

public abstract class AbstractEmailService implements EmailService {

	@Value("${default.remetente}")
	private String remetente;

	@Override
	public void sendEmailConfirmacaoPedido(Pedido pedido) {

		SimpleMailMessage email = prepareSimpleMailMessageFromPedido(pedido);
		sendEmail(email);

	}

	/**
	 * Prepara o corpo do email a partir de um pedido
	 * 
	 * @param pedido
	 * @return
	 */
	protected SimpleMailMessage prepareSimpleMailMessageFromPedido(Pedido pedido) {
		SimpleMailMessage email = new SimpleMailMessage();

		// Destinatário
		email.setTo(pedido.getCliente().getEmail());
		// Remetente (pegando a partir da chave criada em application.properties
		email.setFrom(remetente);
		// Assunto
		email.setSubject("Pedido confirmado! Código: " + pedido.getId());
		// Data do email
		email.setSentDate(new Date(System.currentTimeMillis()));
		// Corpo do email
		email.setText(pedido.toString());

		return email;
	}

}
