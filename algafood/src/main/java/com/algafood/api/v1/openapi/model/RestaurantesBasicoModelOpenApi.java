package com.algafood.api.v1.openapi.model;

import java.util.List;

import org.springframework.hateoas.Links;

import com.algafood.api.v1.model.RestauranteDTO;

import lombok.Data;

@Data
public class RestaurantesBasicoModelOpenApi {
	
	private RestaurantesBasicoEmbeddedModelOpenApi _embedded;
	
	private Links _links;

	
	@Data
	public class RestaurantesBasicoEmbeddedModelOpenApi {
		
		List<RestauranteDTO> restaurantes;
	}
}
