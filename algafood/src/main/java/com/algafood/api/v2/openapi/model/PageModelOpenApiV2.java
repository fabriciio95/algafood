package com.algafood.api.v2.openapi.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageModelOpenApiV2 {

	private Long size;
	
	private Long totalElements;
	
	private Long totalPages;
	
	private Long number;
}
