package com.algafood.api.v1.openapi.model;

import java.util.List;

import org.springframework.hateoas.Links;

import com.algafood.api.v1.model.CidadeDTO;

import lombok.Data;

@Data
public class CidadesModelOpenApi {

	private CidadeEmbeddedModelOpenAPI _embedded;
	
	private Links _links;
	
	@Data
	public class CidadeEmbeddedModelOpenAPI {
		
		private List<CidadeDTO> cidades;
	}
}
