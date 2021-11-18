package com.algafood.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

//@ResponseStatus(value = HttpStatus.NOT_FOUND)//, reason = "Entidade não foi encontrada")
public class EntidadeNaoEncontradaException extends ResponseStatusException {
	private static final long serialVersionUID = 1L;

	 public EntidadeNaoEncontradaException(HttpStatus status, String msg) {
		super(status, msg);
	}
	 
	 public EntidadeNaoEncontradaException(String msg) {
		 this(HttpStatus.NOT_FOUND, msg);
	 }
	 
	 
}
