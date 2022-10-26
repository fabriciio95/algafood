package com.algafood.api.openapi.controller;

import java.util.List;

import com.algafood.api.exceptionhandler.Problem;
import com.algafood.api.model.GrupoDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Usuários")
public interface UsuarioGrupoControllerOpenApi {

	@ApiResponses({
		@ApiResponse(code = 404, message = "Usuário não encontrado", response = Problem.class),
		@ApiResponse(code = 400, message = "ID do usuário inválido", response = Problem.class)
	})
	@ApiOperation("Lista os grupos associados a um usuário")
	List<GrupoDTO> listar(@ApiParam(value = "ID do usuário", example = "1", required = true) Long usuarioId);

	@ApiResponses({
		@ApiResponse(code = 404, message = "Usuário ou grupo não encontrado", response = Problem.class),
		@ApiResponse(code = 204, message = "Desassociação realizada com sucesso")
	})
	@ApiOperation("Desassociação de grupo com usuário")
	void desasociarGrupo(@ApiParam(value = "ID do usuário", example = "1", required = true) Long usuarioId, 
						 @ApiParam(value = "ID do grupo", example = "1", required = true) Long grupoId);

	@ApiResponses({
		@ApiResponse(code = 404, message = "Usuário ou grupo não encontrado", response = Problem.class),
		@ApiResponse(code = 204, message = "Associação realizada com sucesso")
	})
	@ApiOperation("Associação de grupo com usuário")
	void asociarGrupo(@ApiParam(value = "ID do usuário", example = "1", required = true) Long usuarioId, 
			 		  @ApiParam(value = "ID do grupo", example = "1", required = true) Long grupoId);

}