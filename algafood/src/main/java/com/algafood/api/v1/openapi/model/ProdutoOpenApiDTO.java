package com.algafood.api.v1.openapi.model;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(name = "ProdutoDTO")
public class ProdutoOpenApiDTO {

	@Schema(example = "1")
	private Long id;
	
	@Schema(example = "Pizza de Musarella")
	private String nome;
	
	@Schema(example = "Massa italiana com molho de tomate e queijo musarella")
	private String descricao;
	
	@Schema(example = "44.99")
	private BigDecimal preco;
	
	@Schema(example = "true")
	private Boolean ativo;
	
	@Schema(name = "_links")
	private LinksModelOpenApi _links;
}
