package com.algafood.api.model;

import java.math.BigDecimal;

import com.algafood.domain.model.Restaurante;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestauranteDTO {

	private Long id;
	
	private String nome;
	
	private BigDecimal taxaFrete;
	
	private CozinhaDTO cozinha;
	
	public RestauranteDTO (Restaurante restaurante) {
		   this.id = restaurante.getId();
		   this.nome = restaurante.getNome();
		   this.taxaFrete = restaurante.getTaxaFrete();
		   this.cozinha = new CozinhaDTO();
		   this.cozinha.setId(restaurante.getCozinha().getId());
		   this.cozinha.setNome(restaurante.getCozinha().getNome());
	}
	
}
