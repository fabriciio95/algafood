package com.algafood.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;

import com.algafood.api.v1.model.CidadeDTO;
import com.algafood.api.v1.model.input.CidadeInputDTO;

public interface CidadeControllerOpenApi {

	CollectionModel<CidadeDTO> listar();

	CidadeDTO buscar(Long cidadeId);


	CidadeDTO adicionar(CidadeInputDTO cidadeInputDTO);

	CidadeDTO atualizar(CidadeInputDTO cidadeInputDTO, Long cidadeId);

	void excluir(Long cidadeId);

}