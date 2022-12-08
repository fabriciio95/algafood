package com.algafood.api.v2.openapi.model;

import java.util.List;

import org.springframework.hateoas.Links;

import com.algafood.api.v2.model.CozinhaDTOV2;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@ApiModel("CozinhasDTO")
@Getter
@Setter
public class CozinhasDTOOpenApiV2 {

	private CozinhasEmbeddedModelOpenAPI _embedded;
	
	private Links _links;
	
	private PageModelOpenApiV2 page;
	
	@ApiModel("CozinhasEmbeddedModel")
	@Data
	public class CozinhasEmbeddedModelOpenAPI {
		
		private List<CozinhaDTOV2> cozinhas;
	}
}
