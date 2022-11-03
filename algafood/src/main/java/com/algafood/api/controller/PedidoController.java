package com.algafood.api.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
import com.algafood.api.openapi.controller.PedidoControllerOpenApI;
import com.algafood.core.data.PageableTranslator;
import com.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algafood.domain.exception.NegocioException;
import com.algafood.domain.filter.PedidoFilter;
import com.algafood.domain.model.Pedido;
import com.algafood.domain.model.Usuario;
import com.algafood.domain.repository.PedidoRepository;
import com.algafood.domain.service.EmissaoPedidoService;
import com.algafood.infrastructure.repository.spec.PedidoSpecs;

@RestController
@RequestMapping(path = "/pedidos", produces = MediaType.APPLICATION_JSON_VALUE)
public class PedidoController implements PedidoControllerOpenApI {
	
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
	
	@Autowired
	private PagedResourcesAssembler<Pedido> pagedResourcesAssembler;
	
	
	@GetMapping
	public PagedModel<PedidoResumoDTO> pesquisar(PedidoFilter filtro, @PageableDefault(size = 10) Pageable pageable) {
		pageable = traduzirPageable(pageable);
		
		Page<Pedido> pedidosPage = pedidoRepository.findAll(PedidoSpecs.usandoFiltro(filtro), pageable);
		
		return pagedResourcesAssembler.toModel(pedidosPage, pedidoResumoDTOAssembler);
	}	
	
	@GetMapping("/{codigoPedido}")
	public PedidoDTO buscar(@PathVariable String codigoPedido) {
		Pedido pedido = cadastroPedidoService.buscaOuFalhar(codigoPedido);
		
		return pedidoDTOAssembler.toModel(pedido);
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public PedidoDTO adicionar(@RequestBody @Valid PedidoInputDTO pedidoInputDTO) {
		try {
			
			Pedido pedido = pedidoInputDTODisassembler.toDomainObject(pedidoInputDTO);
			
			Usuario cliente = new Usuario();
			cliente.setId(1L);
			pedido.setCliente(cliente);
			
			return pedidoDTOAssembler.toModel(cadastroPedidoService.emitir(pedido));
			
		} catch(EntidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}
	
	private Pageable traduzirPageable(Pageable pageable) {
		var mapeamento = Map.of(
				"codigo", "codigo",
				"subtotal", "subtotal",
				"taxaFrete", "taxaFrete",
				"valorTotal", "valorTotal",
				"status", "status",
				"dataCriacao", "dataCriacao",
				"cliente.nome", "cliente.nome",
				"cliente.id", "cliente.id",
				"restauranteId", "restaurante.id",
				"restaurante.nome", "restaurante.nome"
			 );
		
		return PageableTranslator.translate(pageable, mapeamento);
	}
}
