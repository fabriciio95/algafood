package com.algafood.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;

import com.algafood.api.v1.model.PermissaoDTO;

public interface PermissaoControllerOpenAPI {

	CollectionModel<PermissaoDTO> listar();

}