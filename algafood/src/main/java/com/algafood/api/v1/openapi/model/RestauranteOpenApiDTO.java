package com.algafood.api.v1.openapi.model;

import java.math.BigDecimal;

import com.algafood.api.v1.model.CozinhaDTO;
import com.algafood.api.v1.model.EnderecoDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(name = "RestauranteDTO")
public class RestauranteOpenApiDTO {

	@Schema(example = "1")
	private Long id;
	
	@Schema(example = "Brasileirissimo")
	private String nome;
	
	@Schema(example = "9.99")
	private BigDecimal taxaFrete;
	
	private CozinhaDTO cozinha;
	
	@Schema(example = "true")
	private Boolean ativo;
	
	@Schema(example = "false")
	private Boolean aberto;
	
	private EnderecoDTO endereco;
	
	@Schema(name = "_links")
	private LinksModelOpenApi _links;
}
