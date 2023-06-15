package com.algafood.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;

import com.algafood.api.v1.model.UsuarioDTO;
import com.algafood.api.v1.model.input.SenhaInputDTO;
import com.algafood.api.v1.model.input.UsuarioComSenhaInputDTO;
import com.algafood.api.v1.model.input.UsuarioInputDTO;
import com.algafood.api.v1.openapi.model.UsuariosModelOpenApi;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Usuários")
public interface UsuarioControllerOpenApi {

	@Operation(summary = "Lista os usuários", responses =  {
			@ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = UsuariosModelOpenApi.class)))
	})
	CollectionModel<UsuarioDTO> listar();

	@Operation(summary = "Busca um usuário por ID", responses = {
			@ApiResponse(responseCode = "200"),
			@ApiResponse(responseCode = "400", description = "ID do usuário inválido", 
			    content = @Content(schema = @Schema(ref = "Problema"))),
			@ApiResponse(responseCode = "404", description = "Usuário não encontrado", 
		    content = @Content(schema = @Schema(ref = "Problema")))
	})
	UsuarioDTO buscarPorId(@Parameter(example = "1", 
			description = "ID de um usuário", required = true) Long usuarioId);

	@Operation(summary = "Cadastra um usuário")
	UsuarioDTO adicionar(@RequestBody(description = "Representação de um usuário com senha", required = true) UsuarioComSenhaInputDTO usuarioInputDTO);


	@Operation(summary = "Atualiza um usuário por ID", responses = {
			@ApiResponse(responseCode = "200"),
			@ApiResponse(responseCode = "400", description = "Requisição inválida", 
			    content = @Content(schema = @Schema(ref = "Problema"))),
			@ApiResponse(responseCode = "404", description = "Usuário não encontrado", 
		    content = @Content(schema = @Schema(ref = "Problema")))
	})
	UsuarioDTO atualizar(@RequestBody(description = "Representação de um usuário", required = true) UsuarioInputDTO usuarioInputDTO, @Parameter(example = "1", 
			description = "ID de um usuário", required = true) Long usuarioId);


	@Operation(summary = "Atualiza a senha de um usuário por ID", responses = {
			@ApiResponse(responseCode = "204", description = "Senha alterada com sucesso"),
			@ApiResponse(responseCode = "400", description = "Requisição inválida", 
			    content = @Content(schema = @Schema(ref = "Problema"))),
			@ApiResponse(responseCode = "404", description = "Usuário não encontrado", 
		    content = @Content(schema = @Schema(ref = "Problema")))
	})
	void alterarSenha(@RequestBody(description = "Representação de uma senha", required = true) SenhaInputDTO senhaInputDTO, @Parameter(example = "1", 
			description = "ID de um usuário", required = true) Long usuarioId);

}