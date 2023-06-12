package com.algafood.api.v1.openapi.model;

import java.util.List;

import com.algafood.api.v1.model.RestauranteBasicoDTO;
import com.algafood.api.v1.openapi.model.CidadesModelOpenApi.CidadeEmbeddedModelOpenAPI;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "RestaurantesDTO")
public class RestaurantesModelOpenApi {
	
	@Schema(name = "_embedded")
	private CidadeEmbeddedModelOpenAPI _embedded;
	
	@Schema(name = "_links")
	private LinksModelOpenApi _links;

	@Data
	@Schema(name = "RestauranteEmbeddedDTO")
	public class RestauranteEmbeddedModelOpenAPI {
		
		private List<RestauranteBasicoDTO> cidades;
	}
}
