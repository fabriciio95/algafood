package com.algafood.api.openapi.model;

import java.util.List;

import org.springframework.hateoas.Links;

import com.algafood.api.model.EstadoDTO;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

@ApiModel("EstadosModel")
@Getter
@Setter
public class EstadosModelOpenApi {
	
	private EstadosEmbeddedOpenApi _embedded;
	
	private Links _links;

	
	@ApiModel("EstadosEmbeddedModel")
	@Getter
	@Setter
	public class EstadosEmbeddedOpenApi {
		
		List<EstadoDTO> estados;
	}
}
