package com.zefuinha.spring_ionic_backend.services;

import org.springframework.mail.SimpleMailMessage;

import com.zefuinha.spring_ionic_backend.domain.Pedido;

public interface EmailService {

	/**
	 * Envia email a partir de um pedido, implementado na classe intermediária abstrata
	 * 
	 * @param pedido
	 */
	void sendEmailConfirmacaoPedido(Pedido pedido);

	/**
	 * Envia um email - a ser implementado pela classe que de fato enviará o email,
	 * a fim de permitir formas diferentes de envio de email.
	 * 
	 * @param message
	 */
	void sendEmail(SimpleMailMessage message);

}
