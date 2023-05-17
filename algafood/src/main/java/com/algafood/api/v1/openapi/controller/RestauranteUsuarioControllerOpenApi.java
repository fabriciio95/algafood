package com.algafood.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import com.algafood.api.v1.model.UsuarioDTO;

public interface RestauranteUsuarioControllerOpenApi {

	
	CollectionModel<UsuarioDTO> listar(Long restauranteId);

	ResponseEntity<Void> associarResponsavel(Long restauranteId, Long usuarioId);

	ResponseEntity<Void> deassociarResponsavel(Long restauranteId, Long usuarioId);

}