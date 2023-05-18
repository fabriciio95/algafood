package com.algafood.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import com.algafood.api.v1.model.UsuarioDTO;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name = "security_auth")
public interface RestauranteUsuarioControllerOpenApi {

	
	CollectionModel<UsuarioDTO> listar(Long restauranteId);

	ResponseEntity<Void> associarResponsavel(Long restauranteId, Long usuarioId);

	ResponseEntity<Void> deassociarResponsavel(Long restauranteId, Long usuarioId);

}