package com.algafood.api.v1.openapi.model;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(name = "ItemPedidoDTO")
public class ItemPedidoModelOpenApi {

	@Schema(example = "1")
	private Long produtoId;
	
	@Schema(example = "Pizza")
	private String produtoNome;
	
	@Schema(example = "2")
	private Integer quantidade;
	
	@Schema(example = "34.44")
	private BigDecimal precoUnitario;
	
	@Schema(example = "43.32")
	private BigDecimal precoTotal;
	
	@Schema(example = "Retirar azeitonas")
	private String observacao;
	
	@Schema(name = "_links")
	private LinksModelOpenApi _links;

}
