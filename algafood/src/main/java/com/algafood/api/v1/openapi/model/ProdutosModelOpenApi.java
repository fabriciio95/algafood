package com.algafood.api.v1.openapi.model;

import java.util.List;

import org.springframework.hateoas.Links;

import com.algafood.api.v1.model.ProdutoDTO;

import lombok.Data;

@Data
public class ProdutosModelOpenApi {
	
	private ProdutosEmbeddedModelOpenApi _embedded;
	
	private Links _links;

	
	@Data
	public class ProdutosEmbeddedModelOpenApi {
		
		List<ProdutoDTO> produtos;
	}
}
