package com.algafood.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)//, reason = "Entidade não foi encontrada")
public class EntidadeNaoEncontradaException extends RuntimeException { // ResponseStatusException {
	private static final long serialVersionUID = 1L;

	 public EntidadeNaoEncontradaException(String msg) {
		super(msg);
	}
//	 
//	 public EntidadeNaoEncontradaException(String msg) {
//		 this(HttpStatus.NOT_FOUND, msg);
//	 }
//	 
//	 
}
