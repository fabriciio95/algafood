package com.algafood.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;

import com.algafood.api.v1.model.EstadoDTO;
import com.algafood.api.v1.model.input.EstadoInputDTO;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name = "security_auth")
public interface EstadoControllerOpenApi {

	CollectionModel<EstadoDTO> listar();

	EstadoDTO buscar(Long estadoId);

	EstadoDTO adicionar(EstadoInputDTO estadoInputDTO);

	EstadoDTO atualizar(EstadoInputDTO estadoInputDTO, Long estadoId);

	void excluir(Long estadoId);

}