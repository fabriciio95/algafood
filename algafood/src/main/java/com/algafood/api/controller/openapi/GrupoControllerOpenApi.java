package com.algafood.api.controller.openapi;

import java.util.List;

import com.algafood.api.exceptionhandler.Problem;
import com.algafood.api.model.GrupoDTO;
import com.algafood.api.model.input.GrupoInputDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Grupos")
public interface GrupoControllerOpenApi {

	@ApiOperation("Lista os grupos")
	List<GrupoDTO> listar();

	@ApiResponses({
		@ApiResponse(code = 400, message = "ID de grupo inválido", response = Problem.class),
		@ApiResponse(code = 404, message = "Grupo não encontrado", response = Problem.class)
	})
	@ApiOperation("Busca um grupo por ID")
	GrupoDTO buscarPorId(@ApiParam(value = "ID de um grupo", example = "1") Long grupoId);

	@ApiResponses({
		@ApiResponse(code = 201, message = "Grupo cadastrado"),
	})
	@ApiOperation("Cadastra um grupo")
	GrupoDTO adicionar(@ApiParam(name = "corpo", value = "Representação de um novo grupo") GrupoInputDTO grupoInputDTO);

	@ApiResponses({
		@ApiResponse(code = 200, message = "Grupo atualizado"),
		@ApiResponse(code = 404, message = "Grupo não encontrado", response = Problem.class)
	})
	@ApiOperation("Atualiza um grupo por ID")
	GrupoDTO atualizar(@ApiParam(name = "corpo",
				value = "Representação de um grupo com os novos dados") GrupoInputDTO grupoInputDTO,
				@ApiParam(value = "ID de um grupo", example = "1") Long grupoId);

	@ApiResponses({
		@ApiResponse(code = 204, message = "Cidade excluída", response = Problem.class),
		@ApiResponse(code = 404, message = "Grupo não encontrado", response = Problem.class)
	})
	@ApiOperation("Exclui um grupo por ID")
	void excluir(@ApiParam(value = "ID de um grupo", example = "1") Long grupoId);

}