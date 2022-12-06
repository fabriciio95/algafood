package com.algafood.api.v1.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algafood.api.v1.assembler.ProdutoDTOAssembler;
import com.algafood.api.v1.assembler.ProdutoDTODisassembler;
import com.algafood.api.v1.model.ProdutoDTO;
import com.algafood.api.v1.model.input.ProdutoInputDTO;
import com.algafood.api.v1.openapi.controller.RestauranteProdutoControllerOpenApi;
import com.algafood.api.v1.utils.AlgaLinks;
import com.algafood.domain.model.Produto;
import com.algafood.domain.model.Restaurante;
import com.algafood.domain.repository.ProdutoRepository;
import com.algafood.domain.service.CadastroProdutoService;
import com.algafood.domain.service.CadastroRestauranteService;

@RestController
@RequestMapping(path = "/v1/restaurantes/{restauranteId}/produtos", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestauranteProdutoController implements RestauranteProdutoControllerOpenApi {

	@Autowired
	private CadastroProdutoService cadastroProdutoService;
	
	@Autowired
	private CadastroRestauranteService cadastroRestauranteService;
	
	@Autowired
	private ProdutoDTOAssembler produtoDTOAssembler;
	
	@Autowired
	private ProdutoDTODisassembler produtoDisassembler;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private AlgaLinks algaLinks;

	@GetMapping
	public CollectionModel<ProdutoDTO> listar(@PathVariable Long restauranteId, @RequestParam(required = false, defaultValue = "false") Boolean incluirInativos) {
		Restaurante restaurante = cadastroRestauranteService.buscarOuFalhar(restauranteId);
		List<Produto> todosProdutos;
		if(incluirInativos) {
			todosProdutos = produtoRepository.findAllByRestaurante(restaurante);
		} else {
			todosProdutos = produtoRepository.findAtivosByRestaurante(restaurante);
		}
		
		return produtoDTOAssembler.toCollectionModel(todosProdutos)
				.add(algaLinks.linkToRestauranteProdutos(restauranteId));
	}
	
	@GetMapping("/{produtoId}")
	public ProdutoDTO buscarPorId(@PathVariable Long restauranteId, @PathVariable Long produtoId) {
		Produto produto = cadastroProdutoService.buscarOuFalhar(restauranteId, produtoId);
		
		return produtoDTOAssembler.toModel(produto);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ProdutoDTO adicionar(@PathVariable Long restauranteId, @RequestBody @Valid ProdutoInputDTO produtoInputDTO) {
		Restaurante restaurante = cadastroRestauranteService.buscarOuFalhar(restauranteId);
		
		Produto produto = produtoDisassembler.toDomainObject(produtoInputDTO);
		
		produto.setRestaurante(restaurante);
		
		return produtoDTOAssembler.toModel(cadastroProdutoService.salvar(produto, restauranteId));
	}
	
	@PutMapping("/{produtoId}")
	public ProdutoDTO atualizar(@PathVariable Long restauranteId, @PathVariable Long produtoId,
				@RequestBody @Valid ProdutoInputDTO produtoInputDTO) {
		
		Restaurante restaurante = cadastroRestauranteService.buscarOuFalhar(restauranteId);
		
		Produto produto = cadastroProdutoService.buscarOuFalhar(restaurante.getId(), produtoId);
		
		produtoDisassembler.copyToDomainObject(produtoInputDTO, produto);
		
		return produtoDTOAssembler.toModel(cadastroProdutoService.salvar(produto, restaurante.getId()));
	}
}
