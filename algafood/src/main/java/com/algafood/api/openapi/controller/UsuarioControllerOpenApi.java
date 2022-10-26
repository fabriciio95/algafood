package com.algafood.api.openapi.controller;

import java.util.List;

import com.algafood.api.exceptionhandler.Problem;
import com.algafood.api.model.UsuarioDTO;
import com.algafood.api.model.input.SenhaInputDTO;
import com.algafood.api.model.input.UsuarioComSenhaInputDTO;
import com.algafood.api.model.input.UsuarioInputDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Usuários")
public interface UsuarioControllerOpenApi {

	@ApiOperation("Lista os usuários")
	List<UsuarioDTO> listar();

	@ApiResponses({
		@ApiResponse(code = 404, message = "Usuário não encontrado", response = Problem.class),
		@ApiResponse(code = 400, message = "ID do usuário inválido", response = Problem.class)
	})
	@ApiOperation("Busca um usuário por ID")
	UsuarioDTO buscarPorId(@ApiParam(value = "ID do usuário", example = "1", required = true) Long usuarioId);

	@ApiResponses({
		@ApiResponse(code = 201, message = "Usuário cadastrado")
	})
	@ApiOperation("Cadastra um usuário")
	UsuarioDTO adicionar(@ApiParam(name = "corpo", 
						value = "Representação de um novo usuário", required = true) UsuarioComSenhaInputDTO usuarioInputDTO);

	@ApiResponses({
		@ApiResponse(code = 404, message = "Usuário não encontrado", response = Problem.class),
		@ApiResponse(code = 200, message = "Usuário atualizado")
	})
	@ApiOperation("Atualiza um usuário por ID")
	UsuarioDTO atualizar(@ApiParam(name = "corpo", value = "Representação de um usuário com os novos dados", 
						required = true) UsuarioInputDTO usuarioInputDTO, 
						@ApiParam(value = "ID do usuário", example = "1", required = true) Long usuarioId);

	@ApiResponses({
		@ApiResponse(code = 404, message = "Usuário não encontrado", response = Problem.class),
		@ApiResponse(code = 200, message = "Senha atualizada com sucesso")
	})
	@ApiOperation("Atualiza a senha de um usuário")
	void alterarSenha(@ApiParam(name = "corpo", 
					value = "Representação de uma nova senha", required = true) SenhaInputDTO senhaInputDTO, 
					  @ApiParam(value = "ID do usuário", example = "1", required = true) Long usuarioId);

}