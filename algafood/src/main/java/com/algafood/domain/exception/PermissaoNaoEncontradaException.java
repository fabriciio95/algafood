package com.algafood.domain.exception;

public class PermissaoNaoEncontradaException extends EntidadeNaoEncontradaException { 
	private static final long serialVersionUID = 1L;
	
	public PermissaoNaoEncontradaException(String msg) {
		super(msg);
	}

	 public PermissaoNaoEncontradaException(Long permissaoId) {
		this(String.format("Não existe um cadastro de permissao com o código %d", permissaoId));
	} 
}
