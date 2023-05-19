package com.algafood.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;

import com.algafood.api.v1.model.CidadeDTO;
import com.algafood.api.v1.model.input.CidadeInputDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Cidades")
public interface CidadeControllerOpenApi {

	@Operation(summary = "Lista as cidades")
	CollectionModel<CidadeDTO> listar();

	@Operation(summary = "Busca uma cidade por id")
	CidadeDTO buscar(Long cidadeId);

	@Operation(summary = "Cadastra uma cidade", description = "Cadastro de uma cidade, necessita de um estado e um nome válido")
	CidadeDTO adicionar(CidadeInputDTO cidadeInputDTO);

	@Operation(summary = "Atualiza uma cidade por id")
	CidadeDTO atualizar(CidadeInputDTO cidadeInputDTO, Long cidadeId);

	@Operation(summary = "Exclui uma cidade por id")
	void excluir(Long cidadeId);

}