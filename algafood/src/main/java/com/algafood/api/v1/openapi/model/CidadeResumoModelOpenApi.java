package com.algafood.api.v1.openapi.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(name = "CidadeResumoDTO")
public class CidadeResumoModelOpenApi {

	@Schema(example = "1")
	private Long id;
	
	@Schema(example = "Uberlandia")
	private String nome;
	
	@Schema(example = "Minas Gerais")
	private String estado;
	
	@Schema(name = "_links")
	private LinksModelOpenApi _links;
}
