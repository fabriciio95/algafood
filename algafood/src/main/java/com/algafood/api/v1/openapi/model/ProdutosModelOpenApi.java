package com.algafood.api.v1.openapi.model;

import java.util.List;

import com.algafood.api.v1.model.ProdutoDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "ProdutosDTO")
public class ProdutosModelOpenApi {
	
	@Schema(name = "_embedded")
	private ProdutosEmbeddedModelOpenApi _embedded;
	
	@Schema(name = "_links")
	private LinksModelOpenApi _links;

	
	@Data
	@Schema(name = "ProdutosEmbeddedDTO")
	public class ProdutosEmbeddedModelOpenApi {
		
		List<ProdutoDTO> produtos;
	}
}
