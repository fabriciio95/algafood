package com.algafood.api.model.input;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemPedidoInputDTO {

	@NotNull
	private Long produtoId;
	
	@NotNull
	@Min(1)
	private Integer quantidade;
	
	private String observacao;
	
}
