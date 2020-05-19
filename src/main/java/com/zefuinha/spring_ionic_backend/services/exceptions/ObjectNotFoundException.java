package com.zefuinha.spring_ionic_backend.services.exceptions;

/**
 * Exceção para registro não encontrado
 */
public class ObjectNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ObjectNotFoundException(String msg) {
		super(msg);
	}

	public ObjectNotFoundException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
