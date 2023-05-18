package com.algafood.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;

import com.algafood.api.v1.model.UsuarioDTO;
import com.algafood.api.v1.model.input.SenhaInputDTO;
import com.algafood.api.v1.model.input.UsuarioComSenhaInputDTO;
import com.algafood.api.v1.model.input.UsuarioInputDTO;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name = "security_auth")
public interface UsuarioControllerOpenApi {

	CollectionModel<UsuarioDTO> listar();

	UsuarioDTO buscarPorId(Long usuarioId);

	UsuarioDTO adicionar(UsuarioComSenhaInputDTO usuarioInputDTO);

	UsuarioDTO atualizar(UsuarioInputDTO usuarioInputDTO, Long usuarioId);

	void alterarSenha(SenhaInputDTO senhaInputDTO, Long usuarioId);

}