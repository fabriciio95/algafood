package com.algafood.api.v1.openapi.model;

import java.util.List;

import com.algafood.api.v1.model.GrupoDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "GruposDTO")
public class GruposModelOpenApi {
	
	@Schema(name = "_embedded")
	private GruposEmbeddedModelOpenApi _embedded;
	
	@Schema(name = "_links")
	private LinksModelOpenApi _links;

	
	@Data
	@Schema(name = "GruposEmbeddedDTO")
	public class GruposEmbeddedModelOpenApi {
		
		List<GrupoDTO> grupos;
	}
}
