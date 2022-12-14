package com.algafood.api.v1.openapi.model;

import java.util.List;

import org.springframework.hateoas.Links;

import com.algafood.api.v1.model.FormaPagamentoDTO;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel("FormasPagamentoModel")
@Data
public class FormasPagamentoModelOpenApi {
	
	private FormasPagamentoEmbeddedModelOpenApi _embedded;
	
	private Links _links;

	
	@ApiModel("FormasPagamentoEmbeddedModel")
	@Data
	public class FormasPagamentoEmbeddedModelOpenApi {
		
		List<FormaPagamentoDTO> formasPagamento;
	}
 }