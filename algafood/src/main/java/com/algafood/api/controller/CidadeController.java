package com.algafood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algafood.api.assembler.CidadeDTOAssembler;
import com.algafood.api.assembler.CidadeInputDTODisassembler;
import com.algafood.api.model.CidadeDTO;
import com.algafood.api.model.input.CidadeInputDTO;
import com.algafood.domain.exception.EstadoNaoEncontradoException;
import com.algafood.domain.exception.NegocioException;
import com.algafood.domain.model.Cidade;
import com.algafood.domain.repository.CidadeRepository;
import com.algafood.domain.service.CadastroCidadeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;

@Api(tags = "Cidades")
@RestController
@RequestMapping("/cidades")
@AllArgsConstructor
public class CidadeController {

	private CidadeRepository cidadeRepository;

	private CadastroCidadeService cadastroCidade;
	
	private CidadeDTOAssembler cidadeDTOAssembler;
	
	private CidadeInputDTODisassembler cidadeInputDTODisassembler;

	@ApiOperation("Lista as cidades")
	@GetMapping
	public List<CidadeDTO> listar() {
		return cidadeDTOAssembler.toListDTO(cidadeRepository.findAll());
	}

	@ApiOperation("Busca uma cidade por ID")
	@GetMapping("/{cidadeId}")
	public CidadeDTO buscar(@PathVariable Long cidadeId) {
		return cidadeDTOAssembler.toDTO(cadastroCidade.buscarOuFalhar(cidadeId));
	}

	@ApiOperation("Cadastra uma cidade")
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public CidadeDTO adicionar(@RequestBody @Valid CidadeInputDTO cidadeInputDTO) {
		try {
			
			Cidade cidade = cidadeInputDTODisassembler.toDomainObject(cidadeInputDTO);
			
			return cidadeDTOAssembler.toDTO(cadastroCidade.salvar(cidade));
			
		} catch (EstadoNaoEncontradoException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}

	@ApiOperation("Atualiza uma cidade por ID")
	@PutMapping("/{cidadeId}")
	public CidadeDTO atualizar(@RequestBody @Valid CidadeInputDTO cidadeInputDTO, @PathVariable Long cidadeId) {
		
		Cidade cidadeAtual = cadastroCidade.buscarOuFalhar(cidadeId);

		cidadeInputDTODisassembler.copyToDomainObject(cidadeInputDTO, cidadeAtual);
		
		try {
			
			return cidadeDTOAssembler.toDTO(cadastroCidade.salvar(cidadeAtual));
			
		} catch (EstadoNaoEncontradoException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}

	@ApiOperation("Exclui uma cidade por ID")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{cidadeId}")
	public void excluir(@PathVariable Long cidadeId) {
		cadastroCidade.excluir(cidadeId);
	}
}
