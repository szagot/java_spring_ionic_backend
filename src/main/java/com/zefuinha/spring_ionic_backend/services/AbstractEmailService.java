package com.zefuinha.spring_ionic_backend.services;

import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.zefuinha.spring_ionic_backend.domain.Pedido;

public abstract class AbstractEmailService implements EmailService {

	@Autowired
	private TemplateEngine templateEngine;

	@Autowired
	private JavaMailSender mailSender;

	@Value("${default.remetente}")
	private String remetente;

	@Override
	public void sendEmailConfirmacaoPedido(Pedido pedido) {
		SimpleMailMessage email = prepareSimpleMailMessageFromPedido(pedido);
		sendEmail(email);
	}

	@Override
	public void sendEmailConfirmacaoPedidoHtml(Pedido pedido) {
		try {
			// Tenta enviar no formato html
			sendEmailHtml(prepareMailMimeMessageFromPedido(pedido));
		} catch (MessagingException e) {
			// Se deu erro, envia o email sem HTML
			sendEmailConfirmacaoPedido(pedido);
		}
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

	/**
	 * Prepara um email a partir de um pedido para envio por HTML
	 * 
	 * @param pedido
	 * @return
	 * @throws MessagingException
	 */
	protected MimeMessage prepareMailMimeMessageFromPedido(Pedido pedido) throws MessagingException {
		MimeMessage email = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(email, true);

		// Destinatário
		helper.setTo(pedido.getCliente().getEmail());
		// Remetente (pegando a partir da chave criada em application.properties
		helper.setFrom(remetente);
		// Assunto
		helper.setSubject("Pedido confirmado! Código: " + pedido.getId());
		// Data do email
		helper.setSentDate(new Date(System.currentTimeMillis()));
		// Corpo do email (o true indica que o conteúdo é um html)
		helper.setText(htmlFromTemplatePedido(pedido), true);

		return email;
	}

	/**
	 * Gera um html para envio de email com os dados de um pedido, usando o gerador
	 * de template thymeleaf
	 * 
	 * vide: /src/main/resources/templates/email/confirmacaoPedido.html
	 * 
	 * @param pedido
	 * @return
	 */
	protected String htmlFromTemplatePedido(Pedido pedido) {
		Context context = new Context();
		// Seta a variável do template
		context.setVariable("pedido", pedido);
		// Processando template, retornando o html em forma de string
		return templateEngine.process("email/confirmacaoPedido", context);
	}

}
