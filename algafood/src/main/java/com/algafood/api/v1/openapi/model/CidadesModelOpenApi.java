package com.algafood.api.v1.openapi.model;

import java.util.List;

import org.springframework.hateoas.Links;

import com.algafood.api.v1.model.CidadeDTO;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel("CidadesModel")
@Data
public class CidadesModelOpenApi {

	private CidadeEmbeddedModelOpenAPI _embedded;
	
	private Links _links;
	
	@ApiModel("CidadesEmbeddedModel")
	@Data
	public class CidadeEmbeddedModelOpenAPI {
		
		private List<CidadeDTO> cidades;
	}
}
