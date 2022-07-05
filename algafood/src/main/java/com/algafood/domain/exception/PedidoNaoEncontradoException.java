package com.algafood.domain.exception;

public class PedidoNaoEncontradoException extends EntidadeNaoEncontradaException { 
	private static final long serialVersionUID = 1L;
	
	public PedidoNaoEncontradoException(String msg) {
		super(msg);
	}

	 public PedidoNaoEncontradoException(Long permissaoId) {
		this(String.format("Não existe um cadastro de pedido com o código %d", permissaoId));
	} 
}
