package com.algafood.api.openapi.model;

import java.util.List;

import org.springframework.hateoas.Links;

import com.algafood.api.model.RestauranteDTO;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel("RestaurantesBasicoModel")
@Data
public class RestaurantesBasicoModelOpenApi {
	
	private RestaurantesBasicoEmbeddedModelOpenApi _embedded;
	
	private Links _links;

	
	@ApiModel("RestaurantesBasicoEmbeddedModel")
	@Data
	public class RestaurantesBasicoEmbeddedModelOpenApi {
		
		List<RestauranteDTO> restaurantes;
	}
}
