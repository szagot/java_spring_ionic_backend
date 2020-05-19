package com.zefuinha.spring_ionic_backend.services.exceptions;

/**
 * Exceção para deleção de dados vinculados
 */
public class DataIntegrityException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DataIntegrityException(String msg) {
		super(msg);
	}

	public DataIntegrityException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
