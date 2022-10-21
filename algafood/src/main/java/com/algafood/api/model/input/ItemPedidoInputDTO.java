package com.algafood.api.model.input;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemPedidoInputDTO {

	@ApiModelProperty(example = "1", required = true)
	@NotNull
	private Long produtoId;
	
	@ApiModelProperty(example = "1", required = true)
	@NotNull
	@Min(1)
	private Integer quantidade;
	
	@ApiModelProperty(example = "Menos picante por favor")
	private String observacao;
	
}
