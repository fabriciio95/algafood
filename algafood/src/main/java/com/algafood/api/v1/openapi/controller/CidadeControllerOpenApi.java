package com.algafood.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;

import com.algafood.api.v1.model.CidadeDTO;
import com.algafood.api.v1.model.input.CidadeInputDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Cidades")
public interface CidadeControllerOpenApi {

	@Operation(summary = "Lista as cidades")
	CollectionModel<CidadeDTO> listar();

	@Operation(summary = "Busca uma cidade por id")
	CidadeDTO buscar(@Parameter(description = "ID de uma cidade", example = "1", required = true) Long cidadeId);

	@Operation(summary = "Cadastra uma cidade", description = "Cadastro de uma cidade, necessita de um estado e um nome válido")
	CidadeDTO adicionar(@RequestBody(description = "Representação de uma nova cidade", required = true) CidadeInputDTO cidadeInputDTO);

	@Operation(summary = "Atualiza uma cidade por id")
	CidadeDTO atualizar(@RequestBody(description = "Representação de uma cidade com dados atualizados", required = true) CidadeInputDTO cidadeInputDTO,
			@Parameter(description = "ID de uma cidade", example = "1", required = true) Long cidadeId);

	@Operation(summary = "Exclui uma cidade por id")
	void excluir(@Parameter(description = "ID de uma cidade", example = "1", required = true) Long cidadeId);

}