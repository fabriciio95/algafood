package com.algafood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algafood.api.assembler.PedidoDTOAssembler;
import com.algafood.api.assembler.PedidoInputDTODisassembler;
import com.algafood.api.assembler.PedidoResumoDTOAssembler;
import com.algafood.api.model.PedidoDTO;
import com.algafood.api.model.PedidoResumoDTO;
import com.algafood.api.model.input.PedidoInputDTO;
import com.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algafood.domain.exception.NegocioException;
import com.algafood.domain.model.Pedido;
import com.algafood.domain.model.Usuario;
import com.algafood.domain.repository.PedidoRepository;
import com.algafood.domain.service.EmissaoPedidoService;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
	
	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private EmissaoPedidoService cadastroPedidoService;
	
	@Autowired
	private PedidoDTOAssembler pedidoDTOAssembler;
	
	@Autowired
	private PedidoResumoDTOAssembler pedidoResumoDTOAssembler;
	
	@Autowired
	private PedidoInputDTODisassembler pedidoInputDTODisassembler;
	
	@GetMapping
	public List<PedidoResumoDTO> listar() {
		return pedidoResumoDTOAssembler.toListDTO(pedidoRepository.findAll());
	}
	
	@GetMapping("/{pedidoId}")
	public PedidoDTO buscar(@PathVariable Long pedidoId) {
		Pedido pedido = cadastroPedidoService.buscaOuFalhar(pedidoId);
		
		return pedidoDTOAssembler.toDTO(pedido);
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public PedidoDTO adicionar(@RequestBody @Valid PedidoInputDTO pedidoInputDTO) {
		try {
			
			Pedido pedido = pedidoInputDTODisassembler.toDomainObject(pedidoInputDTO);
			
			Usuario cliente = new Usuario();
			cliente.setId(1L);
			pedido.setCliente(cliente);
			
			return pedidoDTOAssembler.toDTO(cadastroPedidoService.emitir(pedido));
			
		} catch(EntidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}
}
