package com.algafood.client.model.input;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class RestauranteInputModel {

	private String nome;
	
	private BigDecimal taxaFrete;
	
	private CozinhaInputModel cozinha;
	
	private EnderecoInputModel endereco;
}
