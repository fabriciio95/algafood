package com.algafood.api.model.input;

import java.math.BigDecimal;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import com.algafood.domain.model.Cozinha;
import com.algafood.domain.model.Restaurante;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class RestauranteInputDTO {

	@NotBlank
	private String nome;
	
	@NotNull
	@PositiveOrZero
	private BigDecimal taxaFrete;
	
	@Valid
	@NotNull
	private CozinhaIdInputDTO cozinha;
	
	public RestauranteInputDTO(Restaurante restaurante) {
		   this.nome = restaurante.getNome();
		   this.taxaFrete = restaurante.getTaxaFrete();
		   this.cozinha = new CozinhaIdInputDTO();
		   this.cozinha.setId(restaurante.getCozinha().getId());
	}
	
	public Restaurante toModel() {
		Restaurante restaurante = new Restaurante();
		restaurante.setNome(this.nome);
		restaurante.setTaxaFrete(this.taxaFrete);
		
		Cozinha cozinha = new Cozinha();
		
		cozinha.setId(this.getCozinha().getId());
		
		restaurante.setCozinha(cozinha);
		
		return restaurante;
	}
}
