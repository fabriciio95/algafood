package com.algafood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algafood.api.assembler.PedidoDTOAssembler;
import com.algafood.api.assembler.PedidoResumoDTOAssembler;
import com.algafood.api.model.PedidoDTO;
import com.algafood.api.model.PedidoResumoDTO;
import com.algafood.domain.model.Pedido;
import com.algafood.domain.repository.PedidoRepository;
import com.algafood.domain.service.CadastroPedidoService;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
	
	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private CadastroPedidoService cadastroPedidoService;
	
	@Autowired
	private PedidoDTOAssembler pedidoDTOAssembler;
	
	@Autowired
	private PedidoResumoDTOAssembler pedidoResumoDTOAssembler;
	
	@GetMapping
	public List<PedidoResumoDTO> listar() {
		return pedidoResumoDTOAssembler.toListDTO(pedidoRepository.findAll());
	}
	
	@GetMapping("/{pedidoId}")
	public PedidoDTO buscar(@PathVariable Long pedidoId) {
		Pedido pedido = cadastroPedidoService.buscaOuFalhar(pedidoId);
		
		return pedidoDTOAssembler.toDTO(pedido);
	}
}
