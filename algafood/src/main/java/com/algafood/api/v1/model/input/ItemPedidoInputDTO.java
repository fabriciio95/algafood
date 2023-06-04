package com.algafood.api.v1.model.input;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemPedidoInputDTO {

	@Schema(example = "1")
	@NotNull
	private Long produtoId;
	
	@Schema(example = "1")
	@NotNull
	@Min(1)
	private Integer quantidade;
	
	@Schema(example = "Sem molho")
	private String observacao;
	
}
