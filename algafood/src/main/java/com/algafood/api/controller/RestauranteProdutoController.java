package com.algafood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algafood.api.assembler.ProdutoDTOAssembler;
import com.algafood.api.assembler.ProdutoDTODisassembler;
import com.algafood.api.model.ProdutoDTO;
import com.algafood.api.model.input.ProdutoInputDTO;
import com.algafood.domain.model.Produto;
import com.algafood.domain.model.Restaurante;
import com.algafood.domain.repository.ProdutoRepository;
import com.algafood.domain.service.CadastroProdutoService;
import com.algafood.domain.service.CadastroRestauranteService;

@RestController
@RequestMapping("/restaurantes/{restauranteId}/produtos")
public class RestauranteProdutoController {

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

	@GetMapping
	public List<ProdutoDTO> listar(@PathVariable Long restauranteId) {
		Restaurante restaurante = cadastroRestauranteService.buscarOuFalhar(restauranteId);
		
		List<Produto> todosProdutos = produtoRepository.findByRestaurante(restaurante);
		
		return produtoDTOAssembler.toListDTO(todosProdutos);
	}
	
	@GetMapping("/{produtoId}")
	public ProdutoDTO buscarPorId(@PathVariable Long restauranteId, @PathVariable Long produtoId) {
		Produto produto = cadastroProdutoService.buscarOuFalhar(restauranteId, produtoId);
		
		return produtoDTOAssembler.toDTO(produto);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ProdutoDTO adicionar(@PathVariable Long restauranteId, @RequestBody @Valid ProdutoInputDTO produtoInputDTO) {
		Restaurante restaurante = cadastroRestauranteService.buscarOuFalhar(restauranteId);
		
		Produto produto = produtoDisassembler.toDomainObject(produtoInputDTO);
		
		produto.setRestaurante(restaurante);
		
		return produtoDTOAssembler.toDTO(cadastroProdutoService.salvar(produto, restauranteId));
	}
	
	@PutMapping("/{produtoId}")
	public ProdutoDTO atualizar(@PathVariable Long restauranteId, @PathVariable Long produtoId,
				@RequestBody @Valid ProdutoInputDTO produtoInputDTO) {
		
		Restaurante restaurante = cadastroRestauranteService.buscarOuFalhar(restauranteId);
		
		Produto produto = cadastroProdutoService.buscarOuFalhar(restaurante.getId(), produtoId);
		
		produtoDisassembler.copyToDomainObject(produtoInputDTO, produto);
		
		return produtoDTOAssembler.toDTO(cadastroProdutoService.salvar(produto, restaurante.getId()));
	}
}
