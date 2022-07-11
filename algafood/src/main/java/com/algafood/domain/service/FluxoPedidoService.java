package com.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algafood.domain.model.Pedido;

@Service
public class FluxoPedidoService {
	
	@Autowired
	private EmissaoPedidoService emissaoPedidoService;

	@Transactional
	public void confirmar(Long pedidoId) {
		Pedido pedido = emissaoPedidoService.buscaOuFalhar(pedidoId);
		
		pedido.confirmar();
	}

	@Transactional
	public void cancelar(Long pedidoId) {
		Pedido pedido = emissaoPedidoService.buscaOuFalhar(pedidoId);
		
		pedido.cancelar();
	}
	
	@Transactional
	public void entregar(Long pedidoId) {
		Pedido pedido = emissaoPedidoService.buscaOuFalhar(pedidoId);
		
		pedido.entregar();
	}
}
