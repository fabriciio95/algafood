package com.algafood.domain.exception;

//@ResponseStatus(value = HttpStatus.NOT_FOUND)//, reason = "Entidade não foi encontrada")
public abstract class EntidadeNaoEncontradaException extends NegocioException { // ResponseStatusException {
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
