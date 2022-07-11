package com.algafood.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algafood.domain.service.FluxoPedidoService;

@RestController
@RequestMapping("/pedidos/{pedidoId}")
public class FluxoPedidoController {
	
	@Autowired
	private FluxoPedidoService fluxoPedidoService;
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PutMapping("/confirmacao")
	public void confirmar(@PathVariable Long pedidoId) {
		fluxoPedidoService.confirmar(pedidoId);
	}
	
}
