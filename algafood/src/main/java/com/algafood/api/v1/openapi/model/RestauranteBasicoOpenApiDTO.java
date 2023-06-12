package com.algafood.api.v1.openapi.model;

import java.math.BigDecimal;

import com.algafood.api.v1.model.CozinhaDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(name = "RestauranteBasicoDTO")
public class RestauranteBasicoOpenApiDTO {

	@Schema(example = "1")
	private Long id;
	
	@Schema(example = "Brasileirissimo")
	private String nome;
	
	@Schema(example = "9.99")
	private BigDecimal taxaFrete;
	
	private CozinhaDTO cozinha;
	
	@Schema(name = "_links")
	private LinksModelOpenApi _links;
}
