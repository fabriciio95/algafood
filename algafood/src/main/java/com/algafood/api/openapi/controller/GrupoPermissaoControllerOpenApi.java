package com.algafood.api.openapi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import com.algafood.api.exceptionhandler.Problem;
import com.algafood.api.model.PermissaoDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Grupos")
public interface GrupoPermissaoControllerOpenApi {

	@ApiResponses({
		@ApiResponse(code = 404, message = "Grupo não encontrado", response = Problem.class),
		@ApiResponse(code = 400, message = "ID do grupo inválido", response = Problem.class)
	})
	@ApiOperation("Lista as permissões associadas a um grupo")
	CollectionModel<PermissaoDTO> listar(@ApiParam(value = "ID do grupo", example = "1", required = true) Long grupoId);

	@ApiResponses({
		@ApiResponse(code = 404, message = "Grupo ou permissão não encontrada", response = Problem.class),
		@ApiResponse(code = 204, message = "Desassociação realizada com sucesso")
	})
	@ApiOperation("Desassociação de permissão com grupo")
	ResponseEntity<Void> desassociar(@ApiParam(value = "ID do grupo", example = "1", required = true) Long grupoId, 
			         @ApiParam(value = "ID da permissão", example = "1", required = true) Long permissaoId);

	@ApiResponses({
		@ApiResponse(code = 404, message = "Grupo ou permissão não encontrada", response = Problem.class),
		@ApiResponse(code = 204, message = "Associação realizada com sucesso")
	})
	@ApiOperation("Associação de permissão com grupo")
	ResponseEntity<Void> associar(@ApiParam(value = "ID do grupo", example = "1", required = true) Long grupoId, 
	              @ApiParam(value = "ID da permissão", example = "1", required = true) Long permissaoId);

}