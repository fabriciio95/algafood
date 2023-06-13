package com.algafood.api.v1.openapi.model;

import java.util.List;

import com.algafood.api.v1.model.EstadoDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(name = "EstadosDTO")
public class EstadosModelOpenApi {
	
	@Schema(name = "_embedded")
	private EstadosEmbeddedOpenApi _embedded;
	
	@Schema(name = "_links")
	private LinksModelOpenApi _links;

	
	@Getter
	@Setter
	@Schema(name = "EstadosEmbeddedDTo")
	public class EstadosEmbeddedOpenApi {
		
		List<EstadoDTO> estados;
	}
}
