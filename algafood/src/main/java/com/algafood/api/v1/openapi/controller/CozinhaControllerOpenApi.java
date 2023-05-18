package com.algafood.api.v1.openapi.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;

import com.algafood.api.v1.model.CozinhaDTO;
import com.algafood.api.v1.model.input.CozinhaInputDTO;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name = "security_auth")
public interface CozinhaControllerOpenApi {

	
	PagedModel<CozinhaDTO> listar(Pageable pageable);

	CozinhaDTO buscar(Long cozinhaId);

	CozinhaDTO adicionar(CozinhaInputDTO cozinhaInputDTO);

	CozinhaDTO atualizar(Long cozinhaId, CozinhaInputDTO cozinhaInputDTO);

	void excluir(Long cozinhaId);

}