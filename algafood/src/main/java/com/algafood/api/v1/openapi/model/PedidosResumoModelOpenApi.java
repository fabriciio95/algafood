package com.algafood.api.v1.openapi.model;

import java.util.List;

import org.springframework.hateoas.Links;

import com.algafood.api.v1.model.PedidoResumoDTO;

import lombok.Data;

@Data
public class PedidosResumoModelOpenApi {
	
	private PedidosResumoEmbeddedModelOpenApi _embedded;
	
	private Links _links;
	
	private PageModelOpenApi page;

	
	@Data
	public class PedidosResumoEmbeddedModelOpenApi {
		
		List<PedidoResumoDTO> pedidos;
	}
}
