package com.algafood.api.v1.model;

import java.math.BigDecimal;

import org.springframework.hateoas.RepresentationModel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestauranteDTO extends RepresentationModel<RestauranteDTO> {

	private Long id;
	
	private String nome;
	
	private BigDecimal taxaFrete;
	
	private CozinhaDTO cozinha;
	
	private Boolean ativo;
	
	private Boolean aberto;
	
	private EnderecoDTO endereco;
	
}
