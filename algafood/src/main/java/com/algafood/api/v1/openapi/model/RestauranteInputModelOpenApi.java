package com.algafood.api.v1.openapi.model;

import java.math.BigDecimal;

import com.algafood.api.v1.model.input.CozinhaIdInputDTO;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RestauranteInputModelOpenApi {

	private String nome;
	private BigDecimal taxaFrete;
	private CozinhaIdInputDTO cozinha;
	private EnderecoInputModelOpenApi endereco;
}
