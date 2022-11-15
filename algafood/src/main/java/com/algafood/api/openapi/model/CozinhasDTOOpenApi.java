package com.algafood.api.openapi.model;

import java.util.List;

import org.springframework.hateoas.Links;

import com.algafood.api.model.CozinhaDTO;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@ApiModel("CozinhasDTO")
@Getter
@Setter
public class CozinhasDTOOpenApi {

	private CozinhasEmbeddedModelOpenAPI _embedded;
	
	private Links _links;
	
	private PageModelOpenApi page;
	
	@ApiModel("CozinhasEmbeddedModel")
	@Data
	public class CozinhasEmbeddedModelOpenAPI {
		
		private List<CozinhaDTO> cozinhas;
	}
}
