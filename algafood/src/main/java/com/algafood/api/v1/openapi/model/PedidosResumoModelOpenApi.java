package com.algafood.api.v1.openapi.model;

import java.util.List;

import com.algafood.api.v1.model.PedidoResumoDTO;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(name = "PedidosResumoDTO")
@JsonPropertyOrder({ "_embedded", "_links", "page" })
public class PedidosResumoModelOpenApi extends PagedModelOpenApi {
	
	@Schema(name = "_embedded")
	private PedidosResumoEmbeddedModelOpenApi _embedded;
	
	@Getter
	@Setter
	@Schema(name = "PedidosResumoEmbeddedDTO")
	public class PedidosResumoEmbeddedModelOpenApi {
		
		List<PedidoResumoDTO> pedidos;
	}
}
