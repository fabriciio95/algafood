package com.algafood.api.v1.openapi.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PagedModelOpenApi {
	
	@Schema(name = "_links")
	private LinksModelOpenApi _links;

	private PageModelOpenApi page;
}
