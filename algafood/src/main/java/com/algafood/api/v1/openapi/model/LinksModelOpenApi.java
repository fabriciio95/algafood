package com.algafood.api.v1.openapi.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LinksModelOpenApi {
	
	private LinkModel rel;

	@Setter
	@Getter
	private class LinkModel {
		
		@Schema(example = "http://host/cozinhas")
		private String href;
		
		@Schema(example = "true")
		private boolean templated;
		
	}
}
