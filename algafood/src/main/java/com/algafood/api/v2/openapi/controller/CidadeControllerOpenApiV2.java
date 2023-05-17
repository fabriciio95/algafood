package com.algafood.api.v2.openapi.controller;

import org.springframework.hateoas.CollectionModel;

import com.algafood.api.v2.model.CidadeDTOV2;
import com.algafood.api.v2.model.input.CidadeInputDTOV2;

public interface CidadeControllerOpenApiV2 {

	CollectionModel<CidadeDTOV2> listar();

	CidadeDTOV2 buscar(Long cidadeId);

	CidadeDTOV2 adicionar(CidadeInputDTOV2 cidadeInputDTO);

	CidadeDTOV2 atualizar(CidadeInputDTOV2 cidadeInputDTO, Long cidadeId);

	void excluir(Long cidadeId);

}