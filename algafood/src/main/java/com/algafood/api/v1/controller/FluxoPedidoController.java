package com.algafood.api.v1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algafood.api.v1.openapi.controller.FluxoPedidoControllerOpenApi;
import com.algafood.core.security.CheckSecurity;
import com.algafood.domain.service.FluxoPedidoService;

@RestController
@RequestMapping(path = "/v1/pedidos/{codigoPedido}", produces = MediaType.APPLICATION_JSON_VALUE)
public class FluxoPedidoController implements FluxoPedidoControllerOpenApi {
	
	@Autowired
	private FluxoPedidoService fluxoPedidoService;
	
	@CheckSecurity.Pedidos.PodeGerenciarPedidos
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PutMapping("/confirmacao")
	public ResponseEntity<Void> confirmar(@PathVariable String codigoPedido) {
		fluxoPedidoService.confirmar(codigoPedido);
		
		return ResponseEntity.noContent().build();
	}
	
	@CheckSecurity.Pedidos.PodeGerenciarPedidos
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PutMapping("/cancelamento")
	public ResponseEntity<Void> cancelar(@PathVariable String codigoPedido) {
		fluxoPedidoService.cancelar(codigoPedido);
		
		return ResponseEntity.noContent().build();
	}
	
	@CheckSecurity.Pedidos.PodeGerenciarPedidos
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PutMapping("/entrega")
	public ResponseEntity<Void> entregar(@PathVariable String codigoPedido) {
		fluxoPedidoService.entregar(codigoPedido);
		
		return ResponseEntity.noContent().build();
	}
	
}
