package com.algafood.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;

import com.algafood.api.v1.model.PermissaoDTO;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name = "security_auth")
public interface PermissaoControllerOpenAPI {

	CollectionModel<PermissaoDTO> listar();

}