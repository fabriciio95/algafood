package com.algafood.api.v1.openapi.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;

import com.algafood.api.v1.model.CozinhaDTO;
import com.algafood.api.v1.model.input.CozinhaInputDTO;
import com.algafood.core.springdoc.PageableParameter;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name = "security_auth")
public interface CozinhaControllerOpenApi {

	@PageableParameter
	PagedModel<CozinhaDTO> listar(@Parameter(hidden = true) Pageable pageable);

	CozinhaDTO buscar(Long cozinhaId);

	CozinhaDTO adicionar(CozinhaInputDTO cozinhaInputDTO);

	CozinhaDTO atualizar(Long cozinhaId, CozinhaInputDTO cozinhaInputDTO);

	void excluir(Long cozinhaId);

}