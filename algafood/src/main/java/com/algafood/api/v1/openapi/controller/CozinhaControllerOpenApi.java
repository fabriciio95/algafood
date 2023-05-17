package com.algafood.api.v1.openapi.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;

import com.algafood.api.v1.model.CozinhaDTO;
import com.algafood.api.v1.model.input.CozinhaInputDTO;

public interface CozinhaControllerOpenApi {

	
	PagedModel<CozinhaDTO> listar(Pageable pageable);

	CozinhaDTO buscar(Long cozinhaId);

	CozinhaDTO adicionar(CozinhaInputDTO cozinhaInputDTO);

	CozinhaDTO atualizar(Long cozinhaId, CozinhaInputDTO cozinhaInputDTO);

	void excluir(Long cozinhaId);

}