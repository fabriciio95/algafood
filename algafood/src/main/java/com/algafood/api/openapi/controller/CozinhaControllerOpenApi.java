package com.algafood.api.openapi.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.algafood.api.exceptionhandler.Problem;
import com.algafood.api.model.CozinhaDTO;
import com.algafood.api.model.input.CozinhaInputDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Cozinhas")
public interface CozinhaControllerOpenApi {

	
	@ApiOperation("Lista as cozinhas com paginação")
	Page<CozinhaDTO> listar(Pageable pageable);

	@ApiResponses({
		@ApiResponse(code = 400, message = "ID da cozinha inválido", response = Problem.class),
		@ApiResponse(code = 404, message = "Cozinha não encontrada", response = Problem.class)
	})
	@ApiOperation("Busca uma cozinha por ID")
	CozinhaDTO buscar(@ApiParam(value = "ID de uma cozinha", example = "1") Long cozinhaId);

	@ApiResponses({
		@ApiResponse(code = 201, message = "Cozinha cadastrada")
	})
	@ApiOperation("Cadastra uma cozinha")
	CozinhaDTO adicionar(@ApiParam(name = "corpo", 
			value = "Representação de uma nova cozinha")  CozinhaInputDTO cozinhaInputDTO);

	@ApiResponses({
		@ApiResponse(code = 200, message = "Cozinha atualizada"),
		@ApiResponse(code = 404, message = "Cozinha não encontrada", response = Problem.class)
	})
	@ApiOperation("Atualiza uma cozinha por ID")
	CozinhaDTO atualizar(@ApiParam(value = "ID de uma cozinha", example = "1") Long cozinhaId, 
		@ApiParam(name = "corpo", value = "Representação de uma cozinha com os novos dados") CozinhaInputDTO cozinhaInputDTO);

	@ApiResponses({
		@ApiResponse(code = 404, message = "Cozinha não encontrada", response = Problem.class),
		@ApiResponse(code = 204, message = "Cozinha excluída")
	})
	@ApiOperation("Exclui uma cozinha por ID")
	void excluir(@ApiParam(value = "ID de uma cozinha", example = "1") Long cozinhaId);

}