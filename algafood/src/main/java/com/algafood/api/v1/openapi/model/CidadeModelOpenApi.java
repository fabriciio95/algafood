package com.algafood.api.v1.openapi.model;

import com.algafood.api.v1.model.EstadoDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(name = "CidadeDTO")
public class CidadeModelOpenApi {

	@Schema(example = "1")
	private Long id;
	
	@Schema(example = "Uberlândia")
	private String nome;
	
	private EstadoDTO estado;
	
	@Schema(name = "_links")
	private LinksModelOpenApi _links;
}
