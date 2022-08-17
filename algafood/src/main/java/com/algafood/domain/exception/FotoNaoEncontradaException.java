package com.algafood.domain.exception;

public class FotoNaoEncontradaException extends EntidadeNaoEncontradaException { 
	private static final long serialVersionUID = 1L;
	
	public FotoNaoEncontradaException(String msg) {
		super(msg);
	}

	 public FotoNaoEncontradaException(Long produtoId, Long restauranteId) {
		this(String.format("Não existe um cadastro de foto do produto com o código %d para o "
				+ "restaurante de código %d", produtoId, restauranteId));
	} 
}
