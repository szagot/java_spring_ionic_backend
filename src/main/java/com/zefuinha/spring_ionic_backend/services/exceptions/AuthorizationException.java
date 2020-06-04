package com.zefuinha.spring_ionic_backend.services.exceptions;

/**
 * Exceção para usuários não autenticados
 */
public class AuthorizationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public AuthorizationException(String msg) {
		super(msg);
	}

	public AuthorizationException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
