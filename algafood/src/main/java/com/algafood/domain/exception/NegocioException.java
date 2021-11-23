package com.algafood.domain.exception;

//@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
//@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NegocioException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	 public NegocioException(String msg) {
		super(msg);
	}
	 
	 public NegocioException(String msg, Throwable causa) {
		 super(msg, causa);
	 }
}
