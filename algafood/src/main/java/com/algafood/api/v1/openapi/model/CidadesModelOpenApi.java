package com.algafood.api.v1.openapi.model;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "CidadesDTO")
public class CidadesModelOpenApi {

	@Schema(name = "_embedded")
	private CidadeEmbeddedModelOpenAPI _embedded;
	
	@Schema(name = "_links")
	private LinksModelOpenApi _links;
	
	@Data
	@Schema(name = "CidadeEmbeddedDTO")
	public class CidadeEmbeddedModelOpenAPI {
		
		private List<CidadeModelOpenApi> cidades;
	}
}
