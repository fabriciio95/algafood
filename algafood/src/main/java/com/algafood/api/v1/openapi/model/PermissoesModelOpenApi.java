package com.algafood.api.v1.openapi.model;

import java.util.List;

import org.springframework.hateoas.Links;

import com.algafood.api.v1.model.PermissaoDTO;

import lombok.Data;

@Data
public class PermissoesModelOpenApi {
	
	private PermissoesEmbeddedModelOpenApi _embedded;
	
	private Links _links;
 
	@Data
	public class PermissoesEmbeddedModelOpenApi {
		
		List<PermissaoDTO> permissoes;
	}
}
