package com.algafood.api.v1.controller;

import javax.validation.Valid;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algafood.api.utils.ResourceUriHelper;
import com.algafood.api.v1.assembler.CidadeDTOAssembler;
import com.algafood.api.v1.assembler.CidadeInputDTODisassembler;
import com.algafood.api.v1.model.CidadeDTO;
import com.algafood.api.v1.model.input.CidadeInputDTO;
import com.algafood.api.v1.openapi.controller.CidadeControllerOpenApi;
import com.algafood.core.security.CheckSecurity;
import com.algafood.domain.exception.EstadoNaoEncontradoException;
import com.algafood.domain.exception.NegocioException;
import com.algafood.domain.model.Cidade;
import com.algafood.domain.repository.CidadeRepository;
import com.algafood.domain.service.CadastroCidadeService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/v1/cidades", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class CidadeController implements CidadeControllerOpenApi {

	private CidadeRepository cidadeRepository;

	private CadastroCidadeService cadastroCidade;

	private CidadeDTOAssembler cidadeDTOAssembler;

	private CidadeInputDTODisassembler cidadeInputDTODisassembler;

	@CheckSecurity.Cidades.PodeConsultar
	@Deprecated
	@GetMapping
	public CollectionModel<CidadeDTO> listar() {

		CollectionModel<CidadeDTO> cidadesCollectionModel = cidadeDTOAssembler
				.toCollectionModel(cidadeRepository.findAll());

		return cidadesCollectionModel;
	}

	@CheckSecurity.Cidades.PodeConsultar
	@GetMapping("/{cidadeId}")
	public CidadeDTO buscar(@PathVariable Long cidadeId) {

		CidadeDTO cidadeDTO = cidadeDTOAssembler.toModel(cadastroCidade.buscarOuFalhar(cidadeId));
		return cidadeDTO;
	}

	@CheckSecurity.Cidades.PodeEditar
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public CidadeDTO adicionar(@RequestBody @Valid CidadeInputDTO cidadeInputDTO) {
		try {

			Cidade cidade = cidadeInputDTODisassembler.toDomainObject(cidadeInputDTO);

			cidade = cadastroCidade.salvar(cidade);

			CidadeDTO cidadeDTO = cidadeDTOAssembler.toModel(cidade);

			ResourceUriHelper.addUriInResponseHeader(cidade.getId());

			return cidadeDTO;

		} catch (EstadoNaoEncontradoException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}

	@CheckSecurity.Cidades.PodeEditar
	@PutMapping("/{cidadeId}")
	public CidadeDTO atualizar(@RequestBody @Valid CidadeInputDTO cidadeInputDTO, @PathVariable Long cidadeId) {

		Cidade cidadeAtual = cadastroCidade.buscarOuFalhar(cidadeId);

		cidadeInputDTODisassembler.copyToDomainObject(cidadeInputDTO, cidadeAtual);

		try {

			return cidadeDTOAssembler.toModel(cadastroCidade.salvar(cidadeAtual));

		} catch (EstadoNaoEncontradoException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}

	@CheckSecurity.Cidades.PodeEditar
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{cidadeId}")
	public void excluir(@PathVariable Long cidadeId) {
		cadastroCidade.excluir(cidadeId);
	}
}
