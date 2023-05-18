package com.algafood.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import com.algafood.api.v1.model.FormaPagamentoDTO;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name = "security_auth")
public interface RestauranteFormaPagamentoOpenApi {
	
	CollectionModel<FormaPagamentoDTO> listar(Long restauranteId);

	ResponseEntity<Void> desassociar(Long restauranteId, Long formaPagamentoId);

	ResponseEntity<Void> associar(Long restauranteId, Long formaPagamentoId);

}