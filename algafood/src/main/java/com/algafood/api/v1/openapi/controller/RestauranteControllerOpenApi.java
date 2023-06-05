package com.algafood.api.v1.openapi.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import com.algafood.api.v1.model.RestauranteApenasNomeDTO;
import com.algafood.api.v1.model.RestauranteBasicoDTO;
import com.algafood.api.v1.model.RestauranteDTO;
import com.algafood.api.v1.model.input.RestauranteInputDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name = "security_auth")
public interface RestauranteControllerOpenApi {

	@Operation(parameters = {
		@Parameter(name = "projecao", 
				   description = "Nome da projeção",
				   example = "apenas-nome",
				   in = ParameterIn.QUERY,
				   required = false)
	})
	CollectionModel<RestauranteBasicoDTO> listar();

	@Operation(hidden = true)
	CollectionModel<RestauranteApenasNomeDTO> listarApenasNomes();

	RestauranteDTO buscar(Long restauranteId);

	RestauranteDTO adicionar(RestauranteInputDTO restauranteInputDTO);

	RestauranteDTO atualizar(RestauranteInputDTO restauranteInputDTO, Long restauranteId);

	ResponseEntity<Void> ativar(Long restauranteId);

	ResponseEntity<Void> inativar(Long restauranteId);

	void ativarMultiplos(List<Long> restauranteIds);

	void inativarMultiplos(List<Long> restauranteIds);

	ResponseEntity<Void> fechar(Long restauranteId);

	ResponseEntity<Void> abrir(Long restauranteId);

	RestauranteDTO atualizarParcial(Map<String, Object> campos, Long restauranteId, HttpServletRequest request);

	void excluir(Long restauranteId);

}