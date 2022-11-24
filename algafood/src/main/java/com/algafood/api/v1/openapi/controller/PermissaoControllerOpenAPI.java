package com.algafood.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;

import com.algafood.api.v1.model.PermissaoDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Permissões")
public interface PermissaoControllerOpenAPI {

	@ApiOperation("Lista as permissões")
	CollectionModel<PermissaoDTO> listar();

}