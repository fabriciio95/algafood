package com.algafood.api.v1.openapi.model;

import java.util.List;

import org.springframework.hateoas.Links;

import com.algafood.api.v1.model.CozinhaDTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CozinhasDTOOpenApi {

	private CozinhasEmbeddedModelOpenAPI _embedded;
	
	private Links _links;
	
	private PageModelOpenApi page;
	
	@Data
	public class CozinhasEmbeddedModelOpenAPI {
		
		private List<CozinhaDTO> cozinhas;
	}
}
