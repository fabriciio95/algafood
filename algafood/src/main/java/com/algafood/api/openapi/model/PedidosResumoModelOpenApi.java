package com.algafood.api.openapi.model;

import java.util.List;

import org.springframework.hateoas.Links;

import com.algafood.api.model.PedidoResumoDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("PedidosResumoModel")
@Data
public class PedidosResumoModelOpenApi {
	
	@ApiModelProperty(position = 1)
	private PedidosResumoEmbeddedModelOpenApi _embedded;
	
	@ApiModelProperty(position = 5)
	private Links _links;
	
	@ApiModelProperty(position = 10)
	private PageModelOpenApi page;

	
	@ApiModel("PedidosResumoEmbeddedModel")
	@Data
	public class PedidosResumoEmbeddedModelOpenApi {
		
		List<PedidoResumoDTO> pedidos;
	}
}
