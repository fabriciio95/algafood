package com.algafood.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.ServletWebRequest;

import com.algafood.api.v1.model.FormaPagamentoDTO;
import com.algafood.api.v1.model.input.FormaPagamentoInputDTO;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name = "security_auth")
public interface FormaPagamentoControllerOpenApi {

	ResponseEntity<CollectionModel<FormaPagamentoDTO>> listar(ServletWebRequest request);

	ResponseEntity<FormaPagamentoDTO> buscar(Long formaPagamentoId, ServletWebRequest request);

	FormaPagamentoDTO adicionar(FormaPagamentoInputDTO formaPagamentoInputDTO);

	FormaPagamentoDTO atualizar(Long formaPagamentoId, FormaPagamentoInputDTO formaPagamentoInputDTO);

	void excluir(Long formaPagamentoId);

}