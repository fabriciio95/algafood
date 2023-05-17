package com.algafood.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;

import com.algafood.api.v1.model.GrupoDTO;
import com.algafood.api.v1.model.input.GrupoInputDTO;

public interface GrupoControllerOpenApi {

	CollectionModel<GrupoDTO> listar();

	GrupoDTO buscarPorId(Long grupoId);

	GrupoDTO adicionar(GrupoInputDTO grupoInputDTO);

	GrupoDTO atualizar(GrupoInputDTO grupoInputDTO, Long grupoId);

	void excluir(Long grupoId);

}