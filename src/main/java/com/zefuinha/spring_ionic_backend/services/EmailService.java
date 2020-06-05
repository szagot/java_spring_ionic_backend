package com.zefuinha.spring_ionic_backend.services;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;

import com.zefuinha.spring_ionic_backend.domain.Cliente;
import com.zefuinha.spring_ionic_backend.domain.Pedido;

public interface EmailService {

	/**
	 * Envia email a partir de um pedido, implementado na classe intermediária
	 * abstrata
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

	/**
	 * HTML: Envia email a partir de um pedido, implementado na classe intermediária
	 * abstrata
	 * 
	 * @param pedido
	 */
	void sendEmailConfirmacaoPedidoHtml(Pedido pedido);

	/**
	 * HTML: Envia um email - a ser implementado pela classe que de fato enviará o
	 * email, a fim de permitir formas diferentes de envio de email.
	 * 
	 * @param message
	 */
	void sendEmailHtml(MimeMessage message);

	/**
	 * Envia uma nova senha ao cliente
	 * 
	 * @param cliente
	 * @param newPass
	 */
	void sendNewPasswordEmail(Cliente cliente, String newPass);

}
