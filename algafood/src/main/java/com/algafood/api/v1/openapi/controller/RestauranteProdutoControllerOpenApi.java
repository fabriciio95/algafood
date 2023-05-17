package com.algafood.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;

import com.algafood.api.v1.model.ProdutoDTO;
import com.algafood.api.v1.model.input.ProdutoInputDTO;

public interface RestauranteProdutoControllerOpenApi {

	CollectionModel<ProdutoDTO> listar(Long restauranteId, Boolean incluirInativos);

	ProdutoDTO buscarPorId(Long restauranteId, Long produtoId);

	ProdutoDTO adicionar(Long restauranteId, ProdutoInputDTO produtoInputDTO);

	ProdutoDTO atualizar(Long restauranteId, Long produtoId, ProdutoInputDTO produtoInputDTO);

}