package com.algafood.api.v1.model.input;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoInputDTO {

	@Schema(example = "Pizza de Musarella")
	@NotBlank
	private String nome;
	
	@Schema(example = "Massa italiana com molho de tomate e queijo musarella")
	@NotBlank
	private String descricao;
	
	@Schema(example = "44.99")
	@NotNull
	@PositiveOrZero
	private BigDecimal preco;
	
	@Schema(example = "true")
	@NotNull
	private Boolean ativo;
}
