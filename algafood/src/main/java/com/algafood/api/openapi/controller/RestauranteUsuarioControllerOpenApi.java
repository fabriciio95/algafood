package com.algafood.api.openapi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import com.algafood.api.exceptionhandler.Problem;
import com.algafood.api.model.UsuarioDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Restaurantes")
public interface RestauranteUsuarioControllerOpenApi {

	@ApiResponses({
		@ApiResponse(code = 404, message = "Restaurante não encontrado", response = Problem.class)
	})
	@ApiOperation("Lista os usuários responsáveis associados ao restaurante")
	CollectionModel<UsuarioDTO> listar(@ApiParam(value = "ID do restaurante", example = "1", required = true) Long restauranteId);

	@ApiResponses({
		@ApiResponse(code = 404, message = "Restaurante ou usuário não encontrado", response = Problem.class),
		@ApiResponse(code = 204, message = "Associação realizada com sucesso")
	})
	@ApiOperation("Associação de restaurante com usuário responsável")
	ResponseEntity<Void> associarResponsavel(@ApiParam(value = "ID do restaurante", example = "1", required = true) Long restauranteId, 
			@ApiParam(value = "ID do usuário", example = "1", required = true)  Long usuarioId);

	@ApiResponses({
		@ApiResponse(code = 404, message = "Restaurante ou usuário não encontrado", response = Problem.class),
		@ApiResponse(code = 204, message = "Desassociação realizada com sucesso")
	})
	@ApiOperation("Desassociação de restaurante com usuário responsáve")
	ResponseEntity<Void> deassociarResponsavel(@ApiParam(value = "ID do restaurante", example = "1", required = true) Long restauranteId, 
			@ApiParam(value = "ID do usuário", example = "1", required = true)  Long usuarioId);

}