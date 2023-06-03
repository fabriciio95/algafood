package com.algafood.api.v1.openapi.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(name = "CozinhasDTOOpenApi")
@JsonPropertyOrder({ "_embedded", "_links", "page" })
public class CozinhasDTOOpenApi extends PagedModelOpenApi {
	
	@Schema(name = "_embedded")
	private CozinhasEmbeddedpenApi _embedded;
	
	
	@Getter
	@Setter
	private class CozinhasEmbeddedpenApi {
		private List<CozinhaDTOOpenApi> cozinhas;
	}

	@Getter
	@Setter
	public class CozinhaDTOOpenApi {

		@Schema(example = "1")
		private Long id;
	
		@Schema(example = "Brasileira")
		private String nome;
	
		@Schema(name = "_links")
		private LinksModelOpenApi _links;

	}
}
