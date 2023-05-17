package com.algafood.api.v2.openapi.model;

import java.util.List;

import org.springframework.hateoas.Links;

import com.algafood.api.v2.model.CidadeDTOV2;

import lombok.Data;

@Data
public class CidadesModelOpenApiV2 {

	private CidadeEmbeddedModelOpenAPI _embedded;
	
	private Links _links;
	
	@Data
	public class CidadeEmbeddedModelOpenAPI {
		
		private List<CidadeDTOV2> cidades;
	}
}
