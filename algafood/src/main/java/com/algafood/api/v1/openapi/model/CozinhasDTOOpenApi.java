package com.algafood.api.v1.openapi.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(name = "CozinhasDTO")
@JsonPropertyOrder({ "_embedded", "_links", "page" })
public class CozinhasDTOOpenApi extends PagedModelOpenApi {
	
	@Schema(name = "_embedded")
	private CozinhasEmbeddedpenApi _embedded;
	
	@Getter
	@Setter
	@Schema(name = "CozinhasEmbeddedDTO")
	private class CozinhasEmbeddedpenApi {
		private List<CozinhaDTOOpenApi> cozinhas;
	}
}
