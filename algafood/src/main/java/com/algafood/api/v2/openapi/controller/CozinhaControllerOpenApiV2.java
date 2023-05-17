package com.algafood.api.v2.openapi.controller;

import com.algafood.api.v2.model.CozinhaDTOV2;
import com.algafood.api.v2.model.input.CozinhaInputDTOV2;

public interface CozinhaControllerOpenApiV2 {

	CozinhaDTOV2 buscar(Long cozinhaId);

	CozinhaDTOV2 adicionar(CozinhaInputDTOV2 cozinhaInputDTO);

	CozinhaDTOV2 atualizar(Long cozinhaId, CozinhaInputDTOV2 cozinhaInputDTO);

	void excluir(Long cozinhaId);

}