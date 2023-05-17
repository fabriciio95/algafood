package com.algafood.api.v1.openapi.model;

import java.util.List;

import org.springframework.hateoas.Links;

import com.algafood.api.v1.model.FormaPagamentoDTO;

import lombok.Data;

@Data
public class FormasPagamentoModelOpenApi {
	
	private FormasPagamentoEmbeddedModelOpenApi _embedded;
	
	private Links _links;

	
	@Data
	public class FormasPagamentoEmbeddedModelOpenApi {
		
		List<FormaPagamentoDTO> formasPagamento;
	}
 }
