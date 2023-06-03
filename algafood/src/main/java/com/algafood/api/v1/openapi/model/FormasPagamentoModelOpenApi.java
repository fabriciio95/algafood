package com.algafood.api.v1.openapi.model;

import java.util.List;

import com.algafood.api.v1.model.FormaPagamentoDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "FormasPagamentoDTO")
public class FormasPagamentoModelOpenApi {
	
	@Schema(name = "_embedded")
	private FormasPagamentoEmbeddedModelOpenApi _embedded;
	
	@Schema(name = "_links")
	private LinksModelOpenApi _links;

	
	@Data
	@Schema(name = "FormasPagamentoEmbeddedDTO")
	public class FormasPagamentoEmbeddedModelOpenApi {
		
		List<FormaPagamentoDTO> formasPagamento;
	}
 }
