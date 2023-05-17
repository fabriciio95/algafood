package com.algafood.api.v1.openapi.model;

import java.math.BigDecimal;

import com.algafood.api.v1.model.CozinhaDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestauranteBasicoModelOpenApi {

	private Long id;
	
	private String nome;
	
	private BigDecimal taxaFrete;
	
	private CozinhaDTO cozinha;
}
