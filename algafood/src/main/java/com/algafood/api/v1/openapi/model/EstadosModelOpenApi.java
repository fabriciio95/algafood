package com.algafood.api.v1.openapi.model;

import java.util.List;

import org.springframework.hateoas.Links;

import com.algafood.api.v1.model.EstadoDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EstadosModelOpenApi {
	
	private EstadosEmbeddedOpenApi _embedded;
	
	private Links _links;

	
	@Getter
	@Setter
	public class EstadosEmbeddedOpenApi {
		
		List<EstadoDTO> estados;
	}
}
