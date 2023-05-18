package com.algafood.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import com.algafood.api.v1.model.GrupoDTO;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name = "security_auth")
public interface UsuarioGrupoControllerOpenApi {

	
	CollectionModel<GrupoDTO> listar(Long usuarioId);

	ResponseEntity<Void> desasociarGrupo(Long usuarioId, Long grupoId);

	ResponseEntity<Void> asociarGrupo(Long usuarioId, Long grupoId);

}