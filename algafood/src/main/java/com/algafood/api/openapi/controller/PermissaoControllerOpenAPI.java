package com.algafood.api.openapi.controller;

import org.springframework.hateoas.CollectionModel;

import com.algafood.api.model.PermissaoDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Permissões")
public interface PermissaoControllerOpenAPI {

	@ApiOperation("Lista as permissões")
	CollectionModel<PermissaoDTO> listar();

}