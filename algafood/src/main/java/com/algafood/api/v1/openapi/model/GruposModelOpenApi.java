package com.algafood.api.v1.openapi.model;

import java.util.List;

import org.springframework.hateoas.Links;

import com.algafood.api.v1.model.GrupoDTO;

import lombok.Data;

@Data
public class GruposModelOpenApi {
	
	private GruposEmbeddedModelOpenApi _embedded;
	
	private Links _links;

	
	@Data
	public class GruposEmbeddedModelOpenApi {
		
		List<GrupoDTO> grupos;
	}
}
