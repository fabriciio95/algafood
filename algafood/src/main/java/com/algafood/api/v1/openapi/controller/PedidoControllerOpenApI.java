package com.algafood.api.v1.openapi.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;

import com.algafood.api.v1.model.PedidoDTO;
import com.algafood.api.v1.model.PedidoResumoDTO;
import com.algafood.api.v1.model.input.PedidoInputDTO;
import com.algafood.domain.filter.PedidoFilter;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name = "security_auth")
public interface PedidoControllerOpenApI {

	PagedModel<PedidoResumoDTO> pesquisar(PedidoFilter filtro, Pageable pageable);

	PedidoDTO buscar(String codigoPedido);

	PedidoDTO adicionar(PedidoInputDTO pedidoInputDTO);

}