package com.zefuinha.spring_ionic_backend.resources.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.zefuinha.spring_ionic_backend.services.exceptions.DataIntegrityException;
import com.zefuinha.spring_ionic_backend.services.exceptions.ObjectNotFoundException;

/**
 * Responsável por escutar as exceções cadastradas
 */

@ControllerAdvice
public class ResourceExceptionHandler {

	/**
	 * Escuta os erros de registro não encontrado
	 * 
	 * @param e
	 * @param request
	 * @return
	 */
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandarError> objectNotFount(ObjectNotFoundException e, HttpServletRequest request) {

		StandarError err = new StandarError(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);

	}

	/**
	 * Escuta os erros de violação de integridade
	 * 
	 * @param e
	 * @param request
	 * @return
	 */
	@ExceptionHandler(DataIntegrityException.class)
	public ResponseEntity<StandarError> dataIntegrity(DataIntegrityException e, HttpServletRequest request) {

		StandarError err = new StandarError(HttpStatus.BAD_REQUEST.value(), e.getMessage(), System.currentTimeMillis());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);

	}

}
