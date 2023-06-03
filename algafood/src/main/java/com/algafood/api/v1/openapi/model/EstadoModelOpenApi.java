package com.algafood.api.v1.openapi.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(name = "EstadoDTO")
public class EstadoModelOpenApi {

	@Schema(example = "1")
	private Long id;
	
	@Schema(example = "Minas Gerais")
	private String nome;
	
	@Schema(name = "_links")
	private LinksModelOpenApi _links;
}
